package descontos;

import java.util.List;
import java.util.Locale;

/**
 * Testa as três estratégias de desconto com uma compra de R$ 200,00.
 */
public class Main {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    public static void main(String[] args) {
        double valorCompra = 200.00;

        System.out.println("Valor original: " + formatar(valorCompra));
        System.out.println();

        List<EstrategiaDesconto> estrategias = List.of(
                new SemDesconto(),
                new DescontoDezPorCento(),
                new DescontoVintePorCento());

        CalculadoraDesconto calculadora = new CalculadoraDesconto(new SemDesconto());

        for (EstrategiaDesconto estrategia : estrategias) {
            calculadora.setEstrategia(estrategia);
            double valorFinal = calculadora.calcular(valorCompra);
            System.out.println(estrategia.getNome() + ": " + formatar(valorFinal));
        }
    }

    private static String formatar(double valor) {
        return String.format(PT_BR, "R$ %,.2f", valor);
    }
}
