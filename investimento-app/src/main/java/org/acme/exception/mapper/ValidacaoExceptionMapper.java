package org.acme.exception.mapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.dto.response.ErroResponse;

import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ValidacaoExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        List<String> erros = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        ErroResponse erroResponse = new ErroResponse(400, "Body de requisição inválido", erros);

        return Response.status(400)
                .entity(erroResponse)
                .build();
    }
}
