package operacoesMatematicas;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.Map.entry;

public class Simbolo {
    private String valor;
    public static List<String> operadores = Arrays.asList("+", "-", "*", "/");
    public static Map<String, Integer> prioridades = Map.ofEntries(
            entry("(", 0),
            entry(")", 0),
            entry("+", 1),
            entry("-", 1),
            entry("*", 2),
            entry("/", 2)
    );

    public Simbolo(String s) {
        valor = s;
    }

    public String getValor() {
        return valor;
    }

    public boolean isOperando() {
        String regex = "[0-9]+[\\.]?[0-9]*";

        return Pattern.matches(regex, valor);
    }

    public boolean isPonto() {
        return valor.equals(".");
    }

    public boolean isOperador() {
        return operadores.contains(valor);
    }

    public boolean isAbreParenteses() {
        return valor.equals("(");
    }

    public boolean isFechaParenteses() {
        return valor.equals(")");
    }

    public boolean verificaPrioridade(Simbolo outroValor) {
        return prioridades.get(this.valor) >= prioridades.get(outroValor.getValor());
    }
}
