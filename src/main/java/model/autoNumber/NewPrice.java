package model.autoNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class NewPrice {
    private BigDecimal newPrice;
    private Date dateOfPrice;
    private UUID userId;

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public Date getDateOfPrice() {
        return dateOfPrice;
    }

    public void setDateOfPrice(Date dateOfPrice) {
        this.dateOfPrice = dateOfPrice;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
