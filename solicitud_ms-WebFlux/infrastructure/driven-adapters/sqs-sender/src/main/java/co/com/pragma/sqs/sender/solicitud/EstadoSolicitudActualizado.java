package co.com.pragma.sqs.sender.solicitud;

import co.com.pragma.errores.ErrorSQS;
import co.com.pragma.model.estado.Estado;
import co.com.pragma.model.estado.gateways.EstadoRepository;
import co.com.pragma.model.mensaje.gateways.MensajeRepository;
import co.com.pragma.model.solicitud.Solicitud;
import co.com.pragma.model.solicitud.consecuencias.EstadoSolicitudActualizadaMensaje;
import co.com.pragma.model.tipoprestamo.TipoPrestamo;
import co.com.pragma.model.tipoprestamo.gateways.TipoPrestamoRepository;
import co.com.pragma.sqs.sender.SQSSender;
import co.com.pragma.sqs.sender.mapper.SolicitudMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstadoSolicitudActualizado implements MensajeRepository {

    private final EstadoRepository estadoRepository;
    private final TipoPrestamoRepository tipoPrestamoRepository;
    private final SolicitudMapper solicitudMapper;
    private final SQSSender sqsSender;

    @Override
    public Mono<Solicitud> enviarSolicitudActualizada(Solicitud modelo) {
        return Mono.fromCallable(() -> solicitudMapper.toMessage(modelo))
                .flatMap(this::transformarEstadoYTipoPrestamo)
                .flatMap(sqsSender::serializar)
                .flatMap(sqsSender::send)
                .doOnSuccess(token -> log.info("Mensaje enviado con exito : {}", token))
                .onErrorResume(e -> {
                    log.error("Se ha generado un error al enviar mensaje a SQS : {}", e.getMessage());
                    return Mono.error(
                            new ErrorSQS("Se ha generado un error al enviar mensaje a SQS : " + e.getMessage(), Set.of(e.getMessage()))
                    );
                })
                .map(resp -> modelo);
    }

    private Mono<EstadoSolicitudActualizadaMensaje> transformarEstadoYTipoPrestamo(EstadoSolicitudActualizadaMensaje msj){
        Mono<Estado> monoEstado = estadoRepository.obtenerPorId(Long.valueOf(msj.getEstado()));
        Mono<TipoPrestamo> monoTipoPrestamo = tipoPrestamoRepository.obtenerPorId(Long.valueOf(msj.getTipoPrestamo()));
        return Mono.zip(monoEstado, monoTipoPrestamo)
                .map(tupla -> {
                    msj.setTipoPrestamo(tupla.getT2().getNombre());
                    msj.setEstado(tupla.getT1().getNombre());
                    return msj;
                });
    }

}
