package co.com.pragma.api.router;

import co.com.pragma.api.handlers.Handler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

@Configuration
@RequiredArgsConstructor
@Tag(name = "Solicitudes", description = "Operaciones relacionadas con las solicitudes de los usuarios")
public class ActualizarEstadoSolicitudRouter {

    public static final String PATH = "/api/v1/solicitud";
    private final Handler handler;

    @Bean
    public RouterFunction<ServerResponse> actualizarEstadoSolicitudRouterFunction() {
        return RouterFunctions.route(PUT(PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::actualizarEstadoSolicitud);
    }

}
