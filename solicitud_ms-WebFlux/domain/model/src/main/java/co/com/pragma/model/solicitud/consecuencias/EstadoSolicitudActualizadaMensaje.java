package co.com.pragma.model.solicitud.consecuencias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EstadoSolicitudActualizadaMensaje {
    private Long solicitudId;
    private BigDecimal monto;
    private Long plazo;
    private Long estadoId;
    private Long tipoPrestamoId;
    private Long documentoId;
}
