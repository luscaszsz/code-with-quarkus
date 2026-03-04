package org.acme.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroResponse {

    @JsonProperty("codigo")
    private Integer codigo;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("dados_complementares")
    private Collection<String> dadosComplementares;


}
