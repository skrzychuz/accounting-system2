package pl.coderstrust.model;

import java.math.BigDecimal;

/**
 * Created by Nanck on 05.09.2017.
 */
public class Invoice {
    private int id;
    private String descripction;
    private BigDecimal amount = new Money (BigDecimal.ZERO, Currency);

    public Invoice(int id, String descripction, BigDecimal amount) {
        this.id = id;
        this.descripction = descripction;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripction() {
        return descripction;
    }

    public void setDescripction(String descripction) {
        this.descripction = descripction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
