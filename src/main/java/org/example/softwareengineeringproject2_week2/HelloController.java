package org.example.softwareengineeringproject2_week2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.Map;

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

    private Double BMI = 0.0;
    private Boolean validInput = true;
    private Map<String, String> localizedStrings;
    private Locale currentLocale = Locale.getDefault();

    private void setLanguage(Locale locale) {
        lblResult.setText("");
        currentLocale = locale;
        Locale.setDefault(locale);

        localizedStrings = LocalizationService.getLocalizedStrings(locale);

        lblWeight.setText(localizedStrings.getOrDefault("weight", "Weight (kg)"));
        lblHeight.setText(localizedStrings.getOrDefault("height", "Height (cm)"));
        btnCalculate.setText(localizedStrings.getOrDefault("calculate", "Calculate BMI"));
    }

    private void updateResultLabel() {
        if (!validInput) {
            lblResult.setText(localizedStrings.getOrDefault("invalid", "Invalid input!"));
        } else if (BMI == 0.0) {
            lblResult.setText(localizedStrings.getOrDefault("result", "Your BMI is:"));
        } else {
            lblResult.setText(localizedStrings.getOrDefault("result", "Your BMI is:") + " " + String.format("%.2f", BMI));
        }
    }

    public void onEnClick(ActionEvent actionEvent) {
        setLanguage(new Locale("en", "US"));
    }

    public void onFRClick(ActionEvent actionEvent) {
        setLanguage(new Locale("fr", "FR"));
    }

    public void onURClick(ActionEvent actionEvent) {
        setLanguage(new Locale("ur", "PK"));
    }

    public void onVIClick(ActionEvent actionEvent) {
        setLanguage(new Locale("vi", "VI"));
    }

    public void onCalculate(ActionEvent actionEvent) {
        try {
            Double weight = Double.parseDouble(tfWeight.getText());
            Double height = Double.parseDouble(tfHeight.getText());
            height = height / 100;
            BMI = weight / (height * height);
            validInput = true;

            lblResult.setText(localizedStrings.getOrDefault("result", "Your BMI is:") + " " + String.format("%.2f", BMI));

            String language = currentLocale.getLanguage();
            BMIResultService.saveResult(weight, height * 100, BMI, language);

        } catch (NumberFormatException e) {
            validInput = false;
            lblResult.setText(localizedStrings.getOrDefault("invalid", "Invalid input!"));
        }
        updateResultLabel();
    }

    @FXML
    public void initialize() {
        setLanguage(currentLocale);
    }
}
