package cafeteria;

import java.util.Locale;

/**
 * Demonstra a personalização do café empilhando decoradores.
 */
public class Main {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    public static void main(String[] args) {
        Cafe cafe = new CafeSimples();
        exibir("Café simples", cafe);

        cafe = new LeiteDecorator(cafe);
        exibir("Café com leite", cafe);

        cafe = new ChocolateDecorator(cafe);
        exibir("Café com leite e chocolate", cafe);

        System.out.println("Composição do último pedido: " + cafe.getDescricao());
    }

    private static void exibir(String titulo, Cafe cafe) {
        System.out.println(titulo + ": " + String.format(PT_BR, "R$ %,.2f", cafe.getPreco()));
        System.out.println();
    }
}
