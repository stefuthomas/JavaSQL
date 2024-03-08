package converter.controller;

import converter.entity.Converter;
import converter.view.ConverterGUI;
import converter.dao.CurrencyDao;
import java.util.List;

public class ConverterController {
    private ConverterGUI gui;
    private Converter converter = new Converter();
    private CurrencyDao currencyDao = new CurrencyDao();

    public ConverterController(ConverterGUI gui) {
        this.gui = gui;
    }
    public void convert(double amount, String from, String to) {
        double fromRate = currencyDao.getRate(from);
        double toRate = currencyDao.getRate(to);
        String convertedCurrency = converter.convert(amount, fromRate, toRate, to);
        gui.setResult(convertedCurrency);
    }
    public void passCurrencyNamesToGui() {
        List<String> currencyNames = currencyDao.getCurrencyNames();
        gui.setCurrencyNames(currencyNames);

    }
    public static void main(String[] args) {
        ConverterGUI.launch(ConverterGUI.class);
    }
}