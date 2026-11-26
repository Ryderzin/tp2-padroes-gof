package descontos;

/**
 * Contexto do padrão Strategy: usa uma estratégia de desconto sem saber
 * qual é a implementação concreta. Não há if/else para escolher o desconto;
 * basta trocar a estratégia.
 */
public class CalculadoraDesconto {

    private EstrategiaDesconto estrategia;

    public CalculadoraDesconto(EstrategiaDesconto estrategia) {
        this.estrategia = estrategia;
    }

    /** Permite trocar o comportamento em tempo de execução. */
    public void setEstrategia(EstrategiaDesconto estrategia) {
        this.estrategia = estrategia;
    }

    public EstrategiaDesconto getEstrategia() {
        return estrategia;
    }

    public double calcular(double valor) {
        return estrategia.calcularDesconto(valor);
    }
}
