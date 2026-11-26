package cafeteria;

/**
 * Decorator concreto: acrescenta leite (R$ 2,00).
 */
public class LeiteDecorator extends CafeDecorator {

    public LeiteDecorator(Cafe cafe) {
        super(cafe);
    }

    @Override
    public String getDescricao() {
        return cafe.getDescricao() + ", leite";
    }

    @Override
    public double getPreco() {
        return cafe.getPreco() + 2.00;
    }
}
