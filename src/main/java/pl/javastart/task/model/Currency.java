package pl.javastart.task.model;

import java.math.BigDecimal;

public class Currency {
    private String code;
    private BigDecimal exchangeRate;

    public Currency(String code, String exchangeRate) {
        this.code = code;
        this.exchangeRate = new BigDecimal(exchangeRate);
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }
}
