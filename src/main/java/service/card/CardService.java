package service.card;


import model.authorization.User;
import model.card.Card;
import service.base.BaseService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class CardService implements BaseService {
    private ArrayList <Card> cards = new ArrayList<>();


    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public boolean add(Object object) {
        Card newCard = (Card) object;
        boolean ans = false;
        for (Card card:cards) {
            if(card.getCardNumber().equals(newCard.getCardNumber())){
                ans = true;
            }
        }
        if (!ans){
            cards.add(newCard);
        }
        return !ans;
    }

    @Override
    public boolean add(Object object, Object object1) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public void list( ) {

    }
    public BigInteger list(User user) {
        int count = 0;
        BigInteger balance  = BigInteger.valueOf(0);
        for (Card card:cards) {
            if (card.getUserId().equals(user.getId())){
                System.out.println("________________ "+(++count)+" ________________");
                System.out.println("Karta raqami: "+card.getCardNumber());
                System.out.println("Karta balansi: "+card.getBalance().toBigInteger().toString());
                balance = balance.add(card.getBalance().toBigInteger());
            }
        }
        return balance;
    }
    public BigInteger returnBalance(User user) {
        int count = 0;
        BigInteger balance  = BigInteger.valueOf(0);
        for (Card card:cards) {
            if (card.getUserId().equals(user.getId())){
                balance = balance.add(card.getBalance().toBigInteger());
            }
        }
        return balance;
    }
}
