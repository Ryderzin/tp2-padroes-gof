package descontos;

/**
 * Estratégia concreta: reduz o valor da compra em 10%.
 */
public class DescontoDezPorCento implements EstrategiaDesconto {

    @Override
    public double calcularDesconto(double valor) {
        return valor * 0.90;
    }

    @Override
    public String getNome() {
        return "Desconto de 10%";
    }
}
