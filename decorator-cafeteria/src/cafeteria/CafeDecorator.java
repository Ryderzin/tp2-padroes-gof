package cafeteria;

/**
 * Decorator abstrato: também é um Cafe e guarda o café que está "embrulhando".
 * Por padrão, repassa as chamadas ao café embrulhado; as subclasses
 * acrescentam a descrição e o preço do ingrediente.
 */
public abstract class CafeDecorator implements Cafe {

    protected final Cafe cafe;

    protected CafeDecorator(Cafe cafe) {
        this.cafe = cafe;
    }

    @Override
    public String getDescricao() {
        return cafe.getDescricao();
    }

    @Override
    public double getPreco() {
        return cafe.getPreco();
    }
}
