package operacoesMatematicas;

public class Multiplicacao extends Operacao{
    @Override
    public Float calcular(Float... values) {
        return values[0] * values[1];
    }
}
