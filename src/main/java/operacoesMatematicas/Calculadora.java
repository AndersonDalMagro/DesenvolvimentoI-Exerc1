package operacoesMatematicas;
import java.util.*;
import java.util.stream.Collectors;

public class Calculadora {

    private static Map<String, Operacao> operacoes;

    static {
        operacoes = criarOperacoes();
    }

    public static Queue<Simbolo> transformaFilaPosFixa(String line) {
        Queue<Simbolo> filaCaracteres = new LinkedList<>(Arrays.asList(line.split(" ")).stream().map(Simbolo::new).collect(Collectors.toList()));

        Queue<Simbolo> filaPosFixa = new LinkedList<>();
        Stack<Simbolo> pilhaConv = new Stack<>();

        while (!filaCaracteres.isEmpty()) {
            Simbolo simbFila = filaCaracteres.poll();
            if (simbFila.isOperando()) {
                filaPosFixa.add(simbFila);
            } else if (simbFila.isAbreParenteses()) {
                pilhaConv.push(simbFila);
            } else if (simbFila.isOperador()) {
                while (!pilhaConv.isEmpty() && pilhaConv.peek().verificaPrioridade(simbFila)) {
                    filaPosFixa.add(pilhaConv.pop());
                }
                pilhaConv.push(simbFila);
            } else if (simbFila.isFechaParenteses()) {
                while (!pilhaConv.peek().isAbreParenteses()) {
                    filaPosFixa.add(pilhaConv.pop());
                }
                pilhaConv.pop();
            }
        }

        while (!pilhaConv.isEmpty()) {
            filaPosFixa.add(pilhaConv.pop());
        }

        return filaPosFixa;
    }

    public static Stack<Simbolo> calculaFilaPosFixa(Queue<Simbolo> filaPosFixa) {
        Stack<Simbolo> pilhaCalc = new Stack<>();

        while (!filaPosFixa.isEmpty()) {
            Simbolo simbFila = filaPosFixa.poll();

            if (simbFila.isOperando()) {
                pilhaCalc.add(simbFila);
            } else if (simbFila.isOperador()) {
                float operandoA = Float.parseFloat(pilhaCalc.pop().getValor());
                float operandoB = Float.parseFloat(pilhaCalc.pop().getValor());
                Simbolo resultado = new Simbolo(String.valueOf(operacoes.get(simbFila.getValor()).calcular(operandoB, operandoA)));

                pilhaCalc.add(resultado);
            }
        }

        return pilhaCalc;
    }

    private static Map<String, Operacao> criarOperacoes() {
        Map<String, Operacao> operacoes = new HashMap<>();

        operacoes.put("+", new Soma());
        operacoes.put("-", new Subtracao());
        operacoes.put("*", new Multiplicacao());
        operacoes.put("/", new Divisao());

        return operacoes;
    }
}