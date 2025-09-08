package co.com.pragma.sqs.sender;

import co.com.pragma.errores.ErrorSQS;
import co.com.pragma.model.mensaje.gateways.MensajeRepository;
import co.com.pragma.model.solicitud.Solicitud;
import co.com.pragma.sqs.sender.config.SQSSenderProperties;
import co.com.pragma.sqs.sender.mapper.SolicitudMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.Set;

@Service
@Log4j2
@RequiredArgsConstructor
public class SQSSender implements MensajeRepository {

    private final ObjectMapper objectMapper;
    private final SQSSenderProperties properties;
    private final SqsAsyncClient client;
    private final SolicitudMapper solicitudMapper;

    @Override
    public Mono<Solicitud> enviarSolicitudActualizada(Solicitud modelo) {
        return Mono.fromCallable(() -> solicitudMapper.toMessage(modelo))
                .flatMap(this::serializar)
                .flatMap(this::send)
                .doOnSuccess(token -> log.info("Mensaje enviado con exito : {}", token))
                .onErrorResume(e -> {
                    log.error("Se ha generado un error al enviar mensaje a SQS : {}", e.getMessage());
                    return Mono.error(
                            new ErrorSQS("Se ha generado un error al enviar mensaje a SQS : " + e.getMessage(), Set.of(e.getMessage()))
                    );
                })
                .map(resp -> modelo);
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

    private <T> Mono<String> serializar(T object){
        String data = null;
        try{
            data = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e){
            log.error("Error en el proceso de serialización. Error: {}",e.getMessage());
            return Mono.error(new ErrorSQS("Error en el proceso de serialización. Error: " + e.getMessage(), Set.of(e.getMessage())));
        }
        return Mono.just(data);
    }

}
