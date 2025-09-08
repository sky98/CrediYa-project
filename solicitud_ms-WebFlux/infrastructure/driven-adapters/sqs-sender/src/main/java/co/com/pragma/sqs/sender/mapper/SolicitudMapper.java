package co.com.pragma.sqs.sender.mapper;

import co.com.pragma.model.solicitud.Solicitud;
import co.com.pragma.model.solicitud.consecuencias.EstadoSolicitudActualizadaMensaje;

public class SolicitudMapper {

    public EstadoSolicitudActualizadaMensaje toMessage(Solicitud solicitud){
        return EstadoSolicitudActualizadaMensaje.builder()
                .solicitudId(solicitud.getSolicitudId())
                .monto(solicitud.getMonto())
                .plazo(solicitud.getPlazo())
                .documentoId(solicitud.getDocumentoId())
                .tipoPrestamoId(solicitud.getTipoPrestamoId())
                .estadoId(solicitud.getEstadoId())
                .build();
    }

}
