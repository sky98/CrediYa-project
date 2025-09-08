package co.com.pragma.sqs.sender;

import co.com.pragma.errores.ErrorSQS;
import co.com.pragma.model.mensaje.Mensaje;
import co.com.pragma.model.mensaje.gateways.MensajeRepository;
import co.com.pragma.sqs.sender.config.SQSSenderProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;
import software.amazon.awssdk.thirdparty.jackson.core.JsonProcessingException;

import java.util.Set;

@Service
@Log4j2
@RequiredArgsConstructor
public class SQSSender implements MensajeRepository {

    private final ObjectMapper objectMapper;
    private final SQSSenderProperties properties;
    private final SqsAsyncClient client;

    @Override
    public Mono<String> enviarMensajeSQS(Mensaje mensaje) {
        return Mono.fromCallable(() -> objectMapper.writeValueAsString(mensaje))
                .flatMap(this::send)
                .onErrorMap(JsonProcessingException.class, error ->{
                    log.error("Error al serializar mensaje : {}", error.getMessage());
                    return new ErrorSQS("Error al enviar mensaje a SQS", Set.of(error.getMessage()));
                });
    }

    private Mono<String> send(String message) {
        return Mono.fromCallable(() -> buildRequest(message))
                .flatMap(request -> Mono.fromFuture(client.sendMessage(request)))
                .doOnNext(response -> log.debug("Message sent {}", response.messageId()))
                .map(SendMessageResponse::messageId);
    }

    private SendMessageRequest buildRequest(String message) {
        return SendMessageRequest.builder()
                .queueUrl(properties.queueUrl())
                .messageBody(message)
                .build();
    }

}
