package descontos;

/**
 * Estratégia concreta: mantém o valor original da compra.
 */
public class SemDesconto implements EstrategiaDesconto {

    @Override
    public double calcularDesconto(double valor) {
        return valor;
    }

    @Override
    public String getNome() {
        return "Sem desconto";
    }
}
