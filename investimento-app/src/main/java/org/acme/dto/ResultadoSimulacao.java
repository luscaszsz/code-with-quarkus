package org.acme.dto;

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
public class ResultadoSimulacao {

    @NotNull(message = "Valor de aporte é obrigatório!")
    @Positive(message = "Valor de aporte deve ser maior que zero!")
    private BigDecimal valorFinal;

    @NotNull(message = "Prazo do investimento é obrigatório!")
    @Positive(message = "Prazo do investimento deve ser maior que zero!")
    private int prazoMeses;

}
