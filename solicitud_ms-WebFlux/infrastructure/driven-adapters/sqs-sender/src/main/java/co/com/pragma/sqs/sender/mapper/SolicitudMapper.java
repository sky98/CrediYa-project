package co.com.pragma.sqs.sender.mapper;

import co.com.pragma.model.solicitud.Solicitud;
import co.com.pragma.model.solicitud.consecuencias.EstadoSolicitudActualizadaMensaje;
import org.springframework.stereotype.Component;

@Component
public class SolicitudMapper {

    public EstadoSolicitudActualizadaMensaje toMessage(Solicitud solicitud){
        return EstadoSolicitudActualizadaMensaje.builder()
                .solicitudId(solicitud.getSolicitudId())
                .monto(solicitud.getMonto())
                .plazo(solicitud.getPlazo())
                .documentoId(solicitud.getDocumentoId())
                .tipoPrestamo(String.valueOf(solicitud.getTipoPrestamoId()))
                .estado(String.valueOf(solicitud.getEstadoId()))
                .build();
    }

}
