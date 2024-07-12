package View;

import Controller.API;
import Model.Currency;
import java.util.*;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    private static String getUserInput() {
        return scanner.nextLine();
    }

    public static void deployMenu(List<Currency> availableCurrencies) {
        Map<Integer, String> currencyOptions = new HashMap<>();
        int userChoice;

        do {
            System.out.println("\n======================================");
            System.out.println("          Conversor de Monedas          ");
            System.out.println("              by damarizz          ");
            System.out.println("======================================");
            System.out.println("     Desafío Oracle Next Education    ");
            System.out.println("======================================");
            System.out.println("Seleccione su moneda de origen:");

            int optionNumber = 1;
            for (Currency currency : availableCurrencies) {
                System.out.printf("%d) %s [%s]\n", optionNumber, currency.getName(), currency.getCode());
                currencyOptions.put(optionNumber++, currency.getCode());
            }

            System.out.printf("%d) Salir\n", currencyOptions.size() + 1);
            System.out.print("Seleccione una opción: ");

            try {
                userChoice = Integer.parseInt(getUserInput().trim());
                if (userChoice >= 1 && userChoice <= currencyOptions.size()) {
                    deploySubMenu(currencyOptions.get(userChoice), availableCurrencies);
                    waitForUserInput();
                } else if (userChoice == currencyOptions.size() + 1) {
                    System.out.println("\nGracias por usar el Conversor de Monedas. See u soon!:)");
                    break;
                } else {
                    System.out.println("\nOpción no válida. Por favor, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nPor favor, ingrese un número válido.");
                userChoice = 0;
            }
        } while (true);
    }

    private static void deploySubMenu(String sourceCurrency, List<Currency> availableCurrencies) {
        Map<Integer, String> targetCurrencyOptions = new HashMap<>();

        System.out.println("\nSeleccione la moneda de destino:");

        int optionNumber = 1;
        for (Currency currency : availableCurrencies) {
            if (!currency.getCode().equals(sourceCurrency)) {
                System.out.printf("%d) %s [%s]\n", optionNumber, currency.getName(), currency.getCode());
                targetCurrencyOptions.put(optionNumber++, currency.getCode());
            }
        }

        System.out.print("Digite la opción: ");
        try {
            int userChoice = Integer.parseInt(getUserInput().trim());
            if (userChoice >= 1 && userChoice <= targetCurrencyOptions.size()) {
                String targetCurrency = targetCurrencyOptions.get(userChoice);
                double amountToConvert = enterValue(sourceCurrency, targetCurrency);
                System.out.println("\nResultado de la conversión:");
                System.out.println(API.response(sourceCurrency, targetCurrency, amountToConvert));
            } else {
                System.out.println("\nOpción inválida. Volviendo al menú principal.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nOpción inválida. Volviendo al menú principal.");
        }

        System.out.println("\n======================================");
    }

    private static double enterValue(String sourceCurrency, String targetCurrency) {
        while (true) {
            try {
                System.out.printf("Ingrese el monto en %s a convertir a %s: ", sourceCurrency, targetCurrency);
                return Double.parseDouble(getUserInput().trim());
            } catch (NumberFormatException e) {
                System.out.println("\nPor favor, ingrese un monto válido (use punto para decimales).");
            }
        }
    }

    private static void waitForUserInput() {
        System.out.println("\nPresione Enter para continuar...");
        getUserInput();
    }
}