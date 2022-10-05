/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioscalculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Kurdistan
 */
public class IosController implements Initializable {

    @FXML
    private Label outpute;

    private long number1;
    private boolean start;
    private String operator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        start = true;
        operator = "";

    }

    @FXML
    private void processNumPad(ActionEvent event) {
        if (start) {
            outpute.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        outpute.setText(outpute.getText() + value);
    }

    @FXML
    private void processOparator(ActionEvent event) {
        if (outpute.getText().equals("ERROR")) {
            return;
        }
        String value = ((Button) event.getSource()).getText();
        if (!value.equals("=")) {
            /*
             1[+]
             2[-] 
             3[*] 
             4[/] 
            */ 
            if (!operator.isEmpty()) {
                return;
            }
            operator = value;
            number1 = Long.parseLong(outpute.getText());
            outpute.setText("");
        } else {
            if (operator.isEmpty()) {
                return;
            }
            if (outpute.getText().isEmpty()) {
                outpute.setText("ERROR");
                operator = "";
                start = true;
                return;
            }
            outpute.setText(calculate(number1, Long.parseLong(outpute.getText()), operator));
            operator = "";
            start = true;
        }

    }

    private String calculate(long number1, long number2, String opr) {
        switch (opr) {
            case "+":
                return String.valueOf(number1 + number2);
            case "-":
                return String.valueOf(number1 - number2);
            case "x":
                return String.valueOf(number1 * number2);
            case "/":
                if (number2 == 0) {
                    return "ERROR";
                }
                return String.valueOf(number1 / number2);
        }
        return "ERROR";
    }

    @FXML
    private void onActionAC(ActionEvent event) {
        outpute.setText("0");
        start = true;
        operator = "";
    }

}
