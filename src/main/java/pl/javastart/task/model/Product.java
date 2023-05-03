package pl.javastart.task.model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private String currency;

    public Product(String name, String price, String currency) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.currency = currency;
    }

    public Product(String name, BigDecimal price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ", Cena: " + price + " " + currency;
    }
}
