package descontos;

/**
 * Estratégia concreta: reduz o valor da compra em 20%.
 */
public class DescontoVintePorCento implements EstrategiaDesconto {

    @Override
    public double calcularDesconto(double valor) {
        return valor * 0.80;
    }

    @Override
    public String getNome() {
        return "Desconto de 20%";
    }
}
