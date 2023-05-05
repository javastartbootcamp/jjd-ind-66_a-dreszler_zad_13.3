package pl.javastart.task.app;

import pl.javastart.task.io.FileUtils;
import pl.javastart.task.logic.CurrencyCalculator;
import pl.javastart.task.model.Currency;
import pl.javastart.task.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Currency> currencies = FileUtils.loadCurrenciesFromFile("src/main/resources/currencies.csv");
        List<Product> products = FileUtils.loadProductsFromFile("src/main/resources/products.csv");

        if (currencies.size() != 0) {
            CurrencyCalculator currencyCalculator = new CurrencyCalculator(currencies);
            String currencyCode = "EUR";
            Currency outputCurrency = currencyCalculator.getCurrency(currencyCode);
            List<Product> convertedProducts = currencyCalculator.getConvertedProducts(products, outputCurrency);

            BigDecimal sumOfPrices = currencyCalculator.getSumOfPrices(convertedProducts);
            System.out.println("Suma cen wszystkich produktów w " + currencyCode + ": " + sumOfPrices);

            if (!convertedProducts.isEmpty()) {
                BigDecimal meanProductPrice = currencyCalculator.getMeanProductPrice(convertedProducts);
                System.out.println("Średnia wartość produktu w " + currencyCode + ": " + meanProductPrice);

                Product cheapest = currencyCalculator.getCheapestProduct(convertedProducts);
                System.out.println("Najtańszy produkt: " + cheapest);

                Product mostExpensive = currencyCalculator.getMostExpensiveProduct(convertedProducts);
                System.out.println("Nadroższy produkt: " + mostExpensive);
            }
        }
    }
}