package converter.view;

import converter.controller.ConverterController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.List;

public class ConverterGUI extends Application {
    private ConverterController controller;
    private final BorderPane layout = new BorderPane();
    private final HBox top = new HBox();
    private final ComboBox<String> currencyFrom = new ComboBox<>();
    private final ComboBox<String> currencyTo = new ComboBox<>();
    private final TilePane conversion = new TilePane();
    private final TextField currencyAmount = new TextField();
    private final Label currencyResult = new Label();

    public void start(Stage stage) {
        top.setAlignment(Pos.CENTER);
        top.setSpacing(25);

        layout.setTop(top);
        BorderPane.setMargin(top, new Insets(10, 10, 10, 10));

        conversion.setAlignment(Pos.CENTER);
        conversion.setHgap(10);
        conversion.getChildren().add(new Label("Amount :"));
        currencyAmount.setPromptText("Input amount");
        currencyResult.setText("Nothing yet...");

        conversion.getChildren().add(currencyAmount);
        conversion.getChildren().add(currencyResult);

        Button convertButton = new Button("Convert");
        conversion.getChildren().add(convertButton);

        layout.setCenter(conversion);
        BorderPane.setMargin(conversion, new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout, 900, 300);
        scene.getStylesheets().add("style.css");
        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();

        convertButton.setOnAction(actionEvent -> {
            try {
                if (currencyAmount.getText().isEmpty()) {
                    throw new Exception();
                }
            } catch (Exception e) {
                currencyResult.setText("Amount field is empty!");
            }

            try {
                double amount = Double.parseDouble(currencyAmount.getText());
                String from = currencyFrom.getValue();
                String to = currencyTo.getValue();
                if (from.equals(to)) {
                    setResult("No conversion needed!");
                } else {
                    controller.convert(amount, from, to);
                }
            } catch (NumberFormatException e) {
                currencyResult.setText("Invalid input!");
            }
        });
        try {
            controller.passCurrencyNamesToGui();
        } catch (Exception e) {
            currencyResult.setText("Database connection failed.");
        }
        stage.show();
    }

    public void setCurrencyNames(List<String> currencyNames) {
        Label fromLabel = new Label("Select currency from :");
        Label toLabel = new Label("Select currency to :");
        currencyFrom.setItems(FXCollections.observableArrayList(currencyNames));
        currencyTo.setItems(FXCollections.observableArrayList(currencyNames));
        currencyFrom.setValue(currencyNames.get(0));
        currencyTo.setValue(currencyNames.get(1));
        top.getChildren().addAll(fromLabel, currencyFrom, toLabel, currencyTo);

    }

    public void init() {
        controller = new ConverterController(this);
    }

    public void setResult(String result) {
        currencyResult.setText(result);
    }
}