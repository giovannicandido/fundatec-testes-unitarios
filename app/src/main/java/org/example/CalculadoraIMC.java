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

    public String classificar() {
        var imc = calcular();
        if(imc < 17) {
            return "Muito abaixo do peso";
        } else if (imc >= 17 && imc < 18.49) {
            return "Abaixo do peso";
        } else if(imc < 25) {
            return "Peso ideal";
        } else if(imc < 30) {
            return "Acima do peso";
        }

        return "Sem classificação";
    }
}
