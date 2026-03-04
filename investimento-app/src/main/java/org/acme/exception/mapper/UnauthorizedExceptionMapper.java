package org.acme.exception.mapper;

import io.quarkus.security.AuthenticationFailedException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.dto.response.ErroResponse;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class UnauthorizedExceptionMapper
        implements ExceptionMapper<AuthenticationFailedException> {

    @Override
    public Response toResponse(AuthenticationFailedException ex) {

        return Response.status(401)
                .entity(new ErroResponse(401, "Token inválido ou ausente, tente gerar novo jwt", null))
                .build();
    }
}