package operacoesMatematicas;

public class Divisao extends Operacao {
    @Override
    public Float calcular(Float... values) {
        return values[0] / values[1];
    }
}
