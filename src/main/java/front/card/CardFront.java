package front.card;

import front.base.BaseResponse;
import model.authorization.User;
import model.card.Card;
import service.card.CardService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class CardFront extends BaseResponse {
    static Scanner scanner = new Scanner(System.in);

    public static void addCard(CardService cardService, User user) {
        Card card = new Card();
        card.setId();
        scanner = new Scanner(System.in);
        System.out.println("Karta raqamini kiriting: ");
        card.setCardNumber(scanner.nextLine());

        System.out.println("Amal qilish muddatini kiriting: ");
        card.setExpiryDate(scanner.nextLine());

        card.setUserId(user.getId());
        card.setBalance(getBalance());
        boolean add = cardService.add(card);
        response(add);

    }

    public static void balanceFront(User user, CardService cardService) {
        BigInteger balance = cardService.list(user);
        if (balance.compareTo(BigInteger.valueOf(0)) == 0) {
            System.out.println("Siz xali karta qo`shmadingiz.");
            System.out.println("0.Exit      1.Karta qo`shish");
            int stepCode = scanner.nextInt();
            switch (stepCode){
                case 1:
                    addCard(cardService,user);
                    break;
                default:break;
            }
        }else {
            System.out.println("\nSizning balansingiz: "+balance.toString()+" so`m\n0.Exit");
            int strpCode = scanner.nextInt();
        }
    }

    public static BigDecimal getBalance() {
        return BigDecimal.valueOf(Math.random() * 50_000_000L).setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }
}
