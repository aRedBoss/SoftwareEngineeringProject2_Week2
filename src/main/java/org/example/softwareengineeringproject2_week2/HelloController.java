package org.example.softwareengineeringproject2_week2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Label lblWeight;
    @FXML
    private TextField tfWeight;
    @FXML
    private Label lblHeight;
    @FXML
    private TextField tfHeight;
    @FXML
    private Label lblResult;

    @FXML
    private Button btnCalculate;

    @FXML
    private Double BMI = 0.0;

    private Boolean validInput = true;

    private ResourceBundle rb;

/*    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }*/

    public void initialize() {
        onLoadLanguage("en", "US");
    }

    public void onLoadLanguage(String language, String country) {
        Locale myLocale = new Locale(language, country);
        rb = ResourceBundle.getBundle("MessagesBundle", myLocale);
        lblWeight.setText(rb.getString("lblWeight.text"));
        lblHeight.setText(rb.getString("lblHeight.text"));
    }
    private void updateResultLabel() {
        if (!validInput) {
            lblResult.setText(rb.getString("lblInvalidInput.text"));
        } else if (BMI == 0.0) {
            lblResult.setText(rb.getString("lblResult.text"));
        } else {
            lblResult.setText(rb.getString("lblResult.text") + " " + String.format("%.2f", BMI));
        }
    }

    public void onEnClick(ActionEvent actionEvent) {
        onLoadLanguage("en", "US");
        updateResultLabel();
    }

    public void onFRClick(ActionEvent actionEvent) {
        onLoadLanguage("fr", "FR");
        updateResultLabel();
    }

    public void onURClick(ActionEvent actionEvent) {
        onLoadLanguage("ur", "PK");
        updateResultLabel();
    }

    public void onVIClick(ActionEvent actionEvent) {
        onLoadLanguage("vi", "VI");
        updateResultLabel();
    }

    public void onCalculate(ActionEvent actionEvent) {
        try {
            Double weight = Double.parseDouble(tfWeight.getText());
            Double height = Double.parseDouble(tfHeight.getText());
            height = height/100;
            BMI = weight/(height*height);
            lblResult.setText(rb.getString("lblResult.text") + " " + String.format("%.2f", BMI));
            validInput = true;
        } catch (NumberFormatException e) {
            validInput = false;
            lblResult.setText(rb.getString("lblInvalidInput.text"));
        }
        updateResultLabel();
    }
}
