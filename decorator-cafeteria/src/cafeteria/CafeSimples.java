package cafeteria;

/**
 * Componente concreto: o café base, sem adicionais.
 */
public class CafeSimples implements Cafe {

    @Override
    public String getDescricao() {
        return "Café simples";
    }

    @Override
    public double getPreco() {
        return 5.00;
    }
}
