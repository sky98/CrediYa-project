package co.com.pragma.model.mensaje.gateways;

import co.com.pragma.model.mensaje.Mensaje;
import reactor.core.publisher.Mono;

public interface MensajeRepository {
    Mono<String> enviarMensajeSQS(Mensaje mensaje);
}
