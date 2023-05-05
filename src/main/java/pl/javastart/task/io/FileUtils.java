package pl.javastart.task.io;

import pl.javastart.task.model.Currency;
import pl.javastart.task.model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {

    public static List<Currency> loadCurrenciesFromFile(String filePath) {
        List<Currency> currencies = new ArrayList<>();

        try (
                Scanner input = new Scanner(new File(filePath))
        ) {
            while (input.hasNextLine()) {
                String[] splitCurrency = input.nextLine().split(";");
                Currency currency = fillCurrency(splitCurrency);
                if (currency != null) {
                    currencies.add(currency);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Nie znaleziono pliku: " + filePath);
        } catch (NullPointerException e) {
            System.err.println("Nazwa pliku nie może być nullem");
        }

        return currencies;
    }

    private static Currency fillCurrency(String[] splitProduct) {
        Currency currency = null;
        try {
            currency = new Currency(splitProduct[0], splitProduct[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Nie załadowano rekordu: nieprawidłowy format. Prawidłowy format to: kod;kurs");
        } catch (NumberFormatException e) {
            System.err.println("Nie załadowano rekordu: kurs waluty musi być liczbą");
        }

        return currency;
    }

    public static List<Product> loadProductsFromFile(String filePath) {
        List<Product> products = new ArrayList<>();

        try (
                Scanner input = new Scanner(new File(filePath))
        ) {
            while (input.hasNextLine()) {
                String[] splitProduct = input.nextLine().split(";");
                Product product = fillProduct(splitProduct);
                if (product != null) {
                    products.add(product);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Nie znaleziono pliku: " + filePath);
        } catch (NullPointerException e) {
            System.err.println("Nazwa pliku nie może być nullem");
        }

        return products;
    }

    private static Product fillProduct(String[] splitProduct) {
        Product product = null;
        try {
            product = new Product(splitProduct[0], splitProduct[1], splitProduct[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Nie załadowano rekordu: nieprawidłowy format. Prawidłowy format to: nazwa;cena;waluta");
        } catch (NumberFormatException e) {
            System.err.println("Nie załadowano rekordu: cena musi być liczbą");
        }

        return product;
    }
}
