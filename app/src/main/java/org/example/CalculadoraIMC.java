package org.example;

import org.example.model.Pessoa;

import java.math.BigDecimal;

public class CalculadoraIMC {
    private final Pessoa pessoa;

    public CalculadoraIMC(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Double calcular() {
        Double result = pessoa.peso() / (pessoa.altura() * pessoa.altura());
        BigDecimal bigDecimal = new BigDecimal(result);
        BigDecimal rounded = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
        return rounded.doubleValue();


    }

    public ClassificacaoIMC classificar() {
        var imc = calcular();
        if(imc < 17) {
            return ClassificacaoIMC.MUITO_ABAIXO_PESO;
        } else if (imc >= 17 && imc < 18.49) {
            return ClassificacaoIMC.ABAIXO_PESO;
        } else if(imc >= 18.5 && imc < 24.99) {
            return ClassificacaoIMC.PESO_NORMAL;
        } else if(imc >= 25 && imc < 29.99) {
            return ClassificacaoIMC.ACIMA_PESO;
        } else if(imc >= 30 && imc < 34.99) {
            return ClassificacaoIMC.OBESIDADE_I;
        } else if(imc >= 35 && imc < 39.99) {
            return ClassificacaoIMC.OBESIDADE_II;
        } else if(imc >= 40) {
            return ClassificacaoIMC.OBESIDADE_MORBIDA;
        }

        return ClassificacaoIMC.SEM_CLASSIFICACAO;
    }
}
