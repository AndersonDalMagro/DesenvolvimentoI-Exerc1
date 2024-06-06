package com.ds1_ex1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import operacoesMatematicas.Simbolo;

import static operacoesMatematicas.Calculadora.calculaFilaPosFixa;
import static operacoesMatematicas.Calculadora.transformaFilaPosFixa;

public class CalculadoraController {

    @FXML
    private TextField visor;

    @FXML
    private Button answer;

    private String ans;

    public void botao(ActionEvent actionEvent) {
        String newText;

        if(visor.getText().length() > 0) {
            StringBuilder sb = new StringBuilder(visor.getText());
            Simbolo ultimoChar = new Simbolo(String.valueOf(sb.charAt(sb.length() - 1)));
            Simbolo novoChar = new Simbolo(((Button) actionEvent.getSource()).getText());

            if(ultimoChar.isOperando() && novoChar.isOperando()
                    || ultimoChar.isOperando() && novoChar.isPonto()
                    || novoChar.isOperando() && ultimoChar.isPonto()) {
                newText = visor.getText() + novoChar.getValor();
            } else {
                newText = visor.getText() + " " + novoChar.getValor();
            }
        } else {
            newText = ((Button) actionEvent.getSource()).getText();
        }

        visor.setText(newText);
    }

    public void backspace() {
        if(visor.getText().length() > 0) {
            StringBuilder sb = new StringBuilder(visor.getText());
            sb.deleteCharAt(sb.length() - 1);
            visor.setText(sb.toString());
        }
    }

    public void calcular() {
        String result = calculaFilaPosFixa(transformaFilaPosFixa(visor.getText())).peek().getValor();

        visor.setText(result);
        ans = result;
        answer.setDisable(false);
    }

    public void clear() {
        visor.setText("");
    }

    public void porcentagem() {
        visor.setText(visor.getText() + " * " + "0.01");
    }

    public void answer() {
        visor.setText(visor.getText() + "  " + ans + " ");
    }

    public void initialize() {
        answer.setDisable(true);
    }
}