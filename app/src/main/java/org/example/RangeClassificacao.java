package org.example;

import java.util.Objects;

public class RangeClassificacao {
    private final Double min;
    private final Double max;
    private final ClassificacaoIMC classificacao;

    public RangeClassificacao(Double min, Double max, ClassificacaoIMC classificacao) {
        this.min = min;
        this.max = max;
        this.classificacao = classificacao;
    }

    public static RangeClassificacao of(Double min, Double max, ClassificacaoIMC classificacao) {
        return new RangeClassificacao(min, max, classificacao);
    }

    public boolean isInRange(Double value) {
        return value >= min && value < max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RangeClassificacao that = (RangeClassificacao) o;
        return Objects.equals(min, that.min) && Objects.equals(max, that.max) && classificacao == that.classificacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, classificacao);
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public ClassificacaoIMC getClassificacao() {
        return classificacao;
    }
}
