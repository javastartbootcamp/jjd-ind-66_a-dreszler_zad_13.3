package pl.javastart.task.logic;

import pl.javastart.task.model.Currency;
import pl.javastart.task.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCalculator {
    private List<Currency> currencies;

    public CurrencyCalculator(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Product> getConvertedProducts(List<Product> products, Currency outputCurrency) {
        List<Product> productsInGivenCurrency = new ArrayList<>();
        for (Product product : products) {
            BigDecimal convertedPrice = getConvertedPrice(product, outputCurrency);
            if (convertedPrice != null) {
                Product convertedProduct = new Product(product.getName(), convertedPrice, outputCurrency.getCode());
                productsInGivenCurrency.add(convertedProduct);
            } else {
                System.out.println("Nie udało się skonwertować ceny produktu: waluta produktu nie została znaleziona");
            }
        }

        return productsInGivenCurrency;
    }

    private BigDecimal getConvertedPrice(Product product, Currency outputCurrency) {
        BigDecimal convertedPrice = null;
        Currency productCurrency = getCurrency(product.getCurrency());
        if (productCurrency != null) {
            BigDecimal productExchangeRate = productCurrency.getExchangeRate();
            BigDecimal exchangeRate = outputCurrency.getExchangeRate();
            convertedPrice = product.getPrice().divide(productExchangeRate, RoundingMode.HALF_UP).multiply(exchangeRate);
        }

        return convertedPrice;
    }

    public Currency getCurrency(String currencyCode) {
        Currency currency = null;
        for (Currency curr : currencies) {
            if (curr.getCode().equals(currencyCode)) {
                currency = curr;
            }
        }

        return currency;
    }

    public BigDecimal getSumOfPrices(List<Product> productsInGivenCurrency) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : productsInGivenCurrency) {
            sum = sum.add(product.getPrice());
        }

        return sum;
    }

    public BigDecimal getMeanProductPrice(List<Product> productsInGivenCurrency) {
        BigDecimal sumOfPricesInGivenCurrency = getSumOfPrices(productsInGivenCurrency);
        BigDecimal productNumber = BigDecimal.valueOf(productsInGivenCurrency.size());

        return sumOfPricesInGivenCurrency.divide(productNumber, RoundingMode.HALF_UP);
    }

    public Product getCheapestProduct(List<Product> productsInGivenCurrency) {
        Product cheapest = productsInGivenCurrency.get(0);
        for (Product product : productsInGivenCurrency) {
            if (product.getPrice().compareTo(cheapest.getPrice()) < 0) {
                cheapest = product;
            }
        }

        return cheapest;
    }

    public Product getMostExpensiveProduct(List<Product> productsInGivenCurrency) {
        Product mostExpensive = productsInGivenCurrency.get(0);
        for (Product product : productsInGivenCurrency) {
            if (product.getPrice().compareTo(mostExpensive.getPrice()) > 0) {
                mostExpensive = product;
            }
        }

        return mostExpensive;
    }
}
