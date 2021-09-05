package model.authorization;

import model.autoNumber.AutoNumber;
import model.autoNumber.Categories;
import model.base.BaseModel;

import java.util.ArrayList;
import java.util.UUID;

public class User extends BaseModel {
    private String phoneNumber;
    private String password;
    private String email;
    private Categories categories;
    private String region;
    private ArrayList<UUID> favourites = new ArrayList<UUID>();
    private ArrayList<AutoNumber> boughtNumbers = new ArrayList<>();

    public User() {
    }

    public User(String name, String phoneNumber, String password, String email, Categories categories, String region) {
        super(name);
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.categories = categories;
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<UUID> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<UUID> favourites) {
        this.favourites = favourites;
    }
    public ArrayList<AutoNumber> getBoughtNumbers() {
        return boughtNumbers;
    }
    public void setBoughtNumbers(ArrayList<AutoNumber> boughtNumbers) {
        this.boughtNumbers = boughtNumbers;
    }
    @Override
    public String toString() {
        return "User{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", favourites=" + favourites +
                ", boughtNumbers=" + boughtNumbers +
                '}';
    }
}
