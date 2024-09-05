package org.example;

import org.example.model.Pessoa;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CalculadoraIMC {
    public static final double ALTURA_MAXIMA = 3.5;
    public static final int PESO_MAXIMO = 1000;
    public static final int PESO_MINIMO = 15;
    private final Pessoa pessoa;

    private List<RangeClassificacao> classificacoes
            = List.of(
            new RangeClassificacao(Double.MIN_VALUE, 17d, ClassificacaoIMC.MUITO_ABAIXO_PESO),
            new RangeClassificacao(17d, 18.49d, ClassificacaoIMC.ABAIXO_PESO),
            new RangeClassificacao(18.5d, 24.99, ClassificacaoIMC.PESO_NORMAL),
            RangeClassificacao.of(25d, 29.99d, ClassificacaoIMC.ACIMA_PESO),
            RangeClassificacao.of(30d, 34.99d, ClassificacaoIMC.OBESIDADE_I),
            RangeClassificacao.of(35d, 39.99d, ClassificacaoIMC.OBESIDADE_II),
            RangeClassificacao.of(40d, Double.MAX_VALUE, ClassificacaoIMC.OBESIDADE_MORBIDA)

    );

    public CalculadoraIMC(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Double calcular() {

        if (pessoa.altura() >= ALTURA_MAXIMA) {
            return 0d;
        }

        if (pessoa.peso() >= PESO_MAXIMO) {
            return 0d;
        }

        if (pessoa.peso() <= PESO_MINIMO) {
            return 0d;
        }

        Double result = pessoa.peso() / (pessoa.altura() * pessoa.altura());
        BigDecimal bigDecimal = new BigDecimal(result);
        BigDecimal rounded = bigDecimal.setScale(7, BigDecimal.ROUND_DOWN);
        return rounded.doubleValue();


    }

    public ClassificacaoIMC classificar() {
        var imc = calcular();
        if (imc == 0) {
            return ClassificacaoIMC.SEM_CLASSIFICACAO;
        }

        Optional<RangeClassificacao> rangeOptional = classificacoes.stream()
                .filter(range -> range.isInRange(imc))
                .findFirst();
        //if(rangeOptional.isPresent()){
        //    return rangeOptional.get().getClassificacao();
       // }
        //return ClassificacaoIMC.SEM_CLASSIFICACAO

        return rangeOptional.map(RangeClassificacao::getClassificacao)
                .orElse(ClassificacaoIMC.SEM_CLASSIFICACAO);


    }
}
