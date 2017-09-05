package pl.coderstrust.model;

import java.math.BigDecimal;

/**
 * Created by Nanck on 05.09.2017.
 */
public class Currency {
    private BigDecimal amount;
    private Currency currency;

    public Currency(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
