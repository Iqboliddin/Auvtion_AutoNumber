package model.autoNumber;

import model.base.BaseModel;

import java.math.BigDecimal;
import java.util.*;

public class AutoNumber extends BaseModel {
    private String region;
    private String regionNumber;
    private String numbers;
    private String letters;
    private String fullNumber;
    private Categories categories;      // category of number
    private Status status;
    private BigDecimal initialPrice;
    private ArrayList<NewPrice> newPrices = new ArrayList<>();
    private BigDecimal nextPossiblePrice;

    public AutoNumber() {
    }

    public AutoNumber(String region, String regionNumber, String numbers, String letters, String fullNumber, Categories categories, Status status, BigDecimal initialPrice, ArrayList<NewPrice> newPrices, BigDecimal nextPossiblePrice) {
        this.region = region;
        this.regionNumber = regionNumber;
        this.numbers = numbers;
        this.letters = letters;
        this.fullNumber = fullNumber;
        this.categories = categories;
        this.status = status;
        this.initialPrice = initialPrice;
        this.newPrices = newPrices;
        this.nextPossiblePrice = nextPossiblePrice;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(String regionNumber) {
        this.regionNumber = regionNumber;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getFullNumber() {
        return fullNumber;
    }

    public void setFullNumber(String fullNumber) {
        this.fullNumber = fullNumber;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public BigDecimal getNextPossiblePrice() {
        if (this.newPrices.size() > 0) {
            NewPrice newPrice = newPrices.get(newPrices.size() - 1);
            return newPrice.getNewPrice().multiply(BigDecimal.valueOf(1.01));
        }

        return this.initialPrice.multiply(BigDecimal.valueOf(1.01));
    }

    public Date getEndDate() {
        if (newPrices.size() > 0) {
            NewPrice newPrice = newPrices.get(newPrices.size() - 1);
            long time = newPrice.getDateOfPrice().getTime();
           Date date = newPrice.getDateOfPrice();
           date.setTime(time+120000);
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 10);
        return calendar.getTime();
    }

    public ArrayList<NewPrice> getNewPrices() {
        return newPrices;
    }

    public void setNewPrices(ArrayList<NewPrice> newPrices) {
        this.newPrices = newPrices;
    }


}
