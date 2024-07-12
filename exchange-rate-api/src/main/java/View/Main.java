package View;

import Model.Currency;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("USD", "Dolar Americano"));
        currencies.add(new Currency("PEN", "Sol Peruano"));
        currencies.add(new Currency("ARS", "Peso Argentino"));
        currencies.add(new Currency("COP", "Peso Colombiano"));
        currencies.add(new Currency("CLP", "Peso Chileno"));
        currencies.add(new Currency("BRL", "Real Brasileño"));
        currencies.add(new Currency("VES", "Bolivar Venezolano"));

        /*
         * To add more currencies, simply add more Currency objects to the ArrayList.
         *
         * WARNING: The currencies added to this ArrayList must be present in
         * the ExchangeRate-API supported currencies list:
         * https://www.exchangerate-api.com/docs/supported-currencies
         *
         * The code (Currency Code) must match the one mentioned in the documentation.
         * The name can be chosen at your discretion.
         */

        try {

            Menu.deployMenu(currencies);

        } catch (Exception e) {

            System.out.println("Error: " + e);
        }
    }
}