package org.acme.exception.mapper;

import io.quarkus.security.AuthenticationFailedException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.dto.response.ErroResponse;

@Provider
public class UnauthorizedExceptionMapper
        implements ExceptionMapper<AuthenticationFailedException> {

    @Override
    public Response toResponse(AuthenticationFailedException ex) {

        return Response.status(401)
                .entity(new ErroResponse(401, "Token inválido ou ausente, tente gerar novo jwt", null))
                .build();
    }
}