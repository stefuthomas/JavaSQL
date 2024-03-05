package converter.entity;

import java.util.ArrayList;
import java.util.List;

class Currency {
    private String name;
    private String abbreviation;
    private double rate;

    public Currency(String name, String abbreviation, double rate) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public double getRate() {
        return rate;
    }
}

public class Converter {
    private List<Currency> currencies;

    public Converter() {
        currencies = new ArrayList<>();
        currencies.add(new Currency("Euro", "EUR", 1.0));
        currencies.add(new Currency("US Dollar", "USD", 1.08));
        currencies.add(new Currency("British Pound", "GBP", 0.86));
        currencies.add(new Currency("Japanese Yen", "JPY", 116.95));
        currencies.add(new Currency("Swedish Krona", "SEK", 11.24));
    }

    public List<String> getCurrencyNames() {
        List<String> names = new ArrayList<>();
        for (Currency currency : currencies) {
            names.add(currency.getAbbreviation());
        }
        return names;
    }

    public String convert(double amount, String from, String to) {
        double result = 0;
        double fromRate = 0;
        double toRate = 0;
        String abbrevation = null;

        if (from.equals(to)) {
            return "Same currency, no conversion needed.";
        } else {
            for (Currency currency : currencies) {
                if (currency.getAbbreviation().equals(from)) {
                    fromRate = currency.getRate();
                }
                if (currency.getAbbreviation().equals(to)) {
                    toRate = currency.getRate();
                    abbrevation = currency.getAbbreviation();
                }
            }

            result = amount * (toRate / fromRate);


            return String.format("%.2f", result) + " " + abbrevation;
        }
    }
}
