package org.acme.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculadora {

    //valorFinal = valor * (1 + rentabilidade/12) ^ prazo
    public static BigDecimal calculaRendimento(BigDecimal valor, BigDecimal rentabilidade, Integer prazo){

        BigDecimal taxaMensal = rentabilidade.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        return valor.multiply(BigDecimal.ONE.add(taxaMensal)).pow(prazo);
    }
}
