package Controller;

import com.google.gson.*;
import Model.Currency;
import java.net.URI;
import java.net.http.*;
import java.io.InputStream;
import java.util.Properties;

public class API {
    private static final String API_KEY;
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String CONFIG_FILE = "config.properties";
    private static final String API_KEY_PROPERTY = "api.key";

    static {
        API_KEY = loadApiKey();
    }

    private static String loadApiKey() {
        try (InputStream input = API.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            Properties prop = new Properties();
            if (input == null) {
                throw new RuntimeException("Unable to find " + CONFIG_FILE);
            }
            prop.load(input);
            return prop.getProperty(API_KEY_PROPERTY);
        } catch (Exception e) {
            throw new RuntimeException("Error loading API key", e);
        }
    }

    public static String response(String sourceCurrency, String targetCurrency, double amount) {
        try {
            String apiResponse = fetchExchangeRate(sourceCurrency, targetCurrency, amount);
            return parseApiResponse(apiResponse, sourceCurrency, targetCurrency, amount);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private static String fetchExchangeRate(String sourceCurrency, String targetCurrency, double amount) throws Exception {
        URI uri = URI.create(API_BASE_URL + API_KEY + "/pair/" + sourceCurrency + "/" + targetCurrency + "/" + amount);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static String parseApiResponse(String apiResponse, String sourceCurrency, String targetCurrency, double amount) {
        JsonObject jsonResponse = JsonParser.parseString(apiResponse).getAsJsonObject();
        String status = jsonResponse.get("result").getAsString();

        if ("success".equals(status)) {
            double convertedAmount = jsonResponse.get("conversion_result").getAsDouble();
            return formatConversionResult(amount, sourceCurrency, convertedAmount, targetCurrency);
        } else {
            return "Error: API request failed";
        }
    }

    private static String formatConversionResult(double sourceAmount, String sourceCurrency, double targetAmount, String targetCurrency) {
        return String.format("$%.2f %s = $%.2f %s", sourceAmount, sourceCurrency, targetAmount, targetCurrency);
    }
}