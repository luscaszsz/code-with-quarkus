package org.acme.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SimulacaoRequest {

    @NotNull(message = "ID do cliente é obrigatório!")
    private Integer clienteId;

    @NotNull(message = "Valor de aporte é obrigatório!")
    @Positive(message = "Valor de aporte deve ser maior que zero!")
    private BigDecimal valor;

    @NotNull(message = "Prazo do investimento é obrigatório!")
    @Positive(message = "Prazo do investimento deve ser maior que zero!")
    private int prazoMeses;

    @NotBlank(message = "Tipo de produto é obrigatório!")
    private String tipoProduto;

}
