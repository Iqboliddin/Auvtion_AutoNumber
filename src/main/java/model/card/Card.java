package model.card;

import model.base.BaseModel;

import java.math.BigDecimal;
import java.util.UUID;

public class Card extends BaseModel {

    private String CardNumber;
    private String expiryDate;
    private BigDecimal balance;
    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Card() {
    }

    public Card(
            String cardNumber,
            String expiryDate,
            BigDecimal balance
    ) {
        CardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.balance = balance;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
