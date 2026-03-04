package org.acme.exception.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.dto.response.ErroResponse;
import org.acme.exception.NegocialException;

import java.time.Instant;
import java.util.Map;

@Provider
public class NegocialExceptionMapper
        implements ExceptionMapper<NegocialException> {

    @Override
    public Response toResponse(NegocialException exception) {

        ErroResponse erroResponse = new ErroResponse(422, exception.getMessage(), null);
        return Response.status(422)
                .entity(erroResponse)
                .build();
    }
}