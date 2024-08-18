import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Scanner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CurrencyConverter {

    private static final String API_KEY = "your_api_key"; // Replace with your API key
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Currency Converter!");

        // Select base currency
        System.out.print("Enter the base currency (e.g., USD, EUR): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        // Select target currency
        System.out.print("Enter the target currency (e.g., INR, GBP): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Enter amount to convert
        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        // Fetch and display the converted amount
        try {
            double convertedAmount = convertCurrency(baseCurrency, targetCurrency, amount);
            System.out.println(String.format("%.2f %s = %.2f %s", amount, baseCurrency, convertedAmount, targetCurrency));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    // Method to fetch real-time exchange rate and convert currency
    private static double convertCurrency(String baseCurrency, String targetCurrency, double amount) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + baseCurrency + "?access_key=" + API_KEY))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            // Parse JSON response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());
            JsonNode ratesNode = rootNode.path("rates");

            if (ratesNode.has(targetCurrency)) {
                double exchangeRate = ratesNode.path(targetCurrency).asDouble();
                return amount * exchangeRate;
            } else {
                throw new Exception("Target currency not found.");
            }
        } else {
            throw new Exception("Error fetching exchange rates.");
        }
    }
}
