package org.acme.exception.mapper;

import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.dto.response.ErroResponse;

@Provider
public class ProibidoExceptionMapper
        implements ExceptionMapper<ForbiddenException> {

    @Override
    public Response toResponse(jakarta.ws.rs.ForbiddenException ex) {

        return Response.status(403)
                .entity(new ErroResponse(403, "Token válido, mas sem acesso ao recurso", null))
                .build();
    }
}