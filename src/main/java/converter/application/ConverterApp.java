package converter.application;
import converter.entity.Converter;
import converter.view.ConverterGUI;

import java.util.List;

public class ConverterApp {
    private ConverterGUI gui;
    private Converter converter = new Converter();

    public ConverterApp(ConverterGUI gui) {
        this.gui = gui;
    }

    public void convert(double amount, String from, String to) {
        String convertedCurrency = converter.convert(amount, from, to);
        gui.setResult(convertedCurrency);
    }

    public void passCurrencyNamesToGui() {
        List<String> currencyNames = converter.getCurrencyNames();
        gui.setCurrencyNames(currencyNames);
    }
    public static void main(String[] args) {
        ConverterGUI.launch(ConverterGUI.class);
    }
}
