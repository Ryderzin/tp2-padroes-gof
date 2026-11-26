package descontos;

/**
 * Strategy: define o contrato comum a todas as formas de calcular desconto.
 */
public interface EstrategiaDesconto {

    /**
     * Aplica o desconto sobre o valor da compra.
     *
     * @param valor valor original da compra
     * @return valor final, já com o desconto aplicado
     */
    double calcularDesconto(double valor);

    /** Nome da estratégia, usado para exibir o resultado. */
    String getNome();
}
