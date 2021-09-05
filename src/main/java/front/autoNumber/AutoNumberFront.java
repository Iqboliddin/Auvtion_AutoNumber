package front.autoNumber;

import front.base.BaseResponse;
import front.card.CardFront;
import model.authorization.User;
import model.autoNumber.AutoNumber;
import model.autoNumber.Categories;
import model.autoNumber.Status;
import model.card.Card;
import service.authorization.UserService;
import service.autoNumber.AutoNumberService;
import service.card.CardService;

import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;


public class AutoNumberFront extends BaseResponse {

    static int MAX = 26;

    public static void addNumber(AutoNumberService autoNumberService) {
        for (int i = 0; i < 1000; i++) {
            int number = (int) (Math.random() * 1000);
            String numbers = putZero(number);
            String letters = printRandomString();
            BigDecimal initoalPrice = BigDecimal.valueOf(getPrice(numbers,letters));
            putRegionNumber(initoalPrice,numbers,letters,autoNumberService);
        }
    }

    private static String putZero(int number) {
        if (number < 10)
            return "00" + number;
        if (number < 100)
            return "0" + number;
        return Integer.toString(number);
    }

    static String printRandomString() {

        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u',
                'v', 'w', 'x', 'y', 'z'};

        String res = "";
        for (int i = 0; i < 3; i++)
            res = res + alphabet[(int) (Math.random() * 10 % MAX)];

        return res.toUpperCase();
    }

    private static double getPrice(String number, String letter) {
        int countNumber = 0;
        int countLetter = 0;

        if (number.charAt(0) == number.charAt(1))
            countNumber++;
        if (number.charAt(0) == number.charAt(2))
            countNumber++;
        if (number.charAt(1) == number.charAt(2))
            countNumber++;

        if (letter.charAt(0) == letter.charAt(1))
            countLetter++;
        if (letter.charAt(0) == letter.charAt(2))
            countLetter++;
        if (letter.charAt(1) == letter.charAt(2))
            countLetter++;

        if (countNumber == 3 && countLetter == 3)
            return 50_000_000;

        if (countNumber == 3)
            return 25_000_000;

        if (countNumber > 0 && countLetter >0 || countLetter == 3 )
            return 5_000_000;

        return 1_000_000;
    }

    private static void putRegionNumber(BigDecimal initoalPrice, String numbers,String letters, AutoNumberService autoNumberService) {
        ArrayList<String> regionNumber = new ArrayList<>();
        ArrayList<String> region = new ArrayList<>();
        regionNumber.add("01");      region.add("TOSHKENT_SHAHAR");
        regionNumber.add("10");      region.add("TOSHKENT_VILOYATI");
        regionNumber.add("20");      region.add("SIRDARYO");
        regionNumber.add("25");      region.add("JIZZAX");
        regionNumber.add("30");      region.add("SAMARQAND");
        regionNumber.add("40");      region.add("FARG'ONA");
        regionNumber.add("50");      region.add("NAMANGAN");
        regionNumber.add("60");      region.add("ANDIJON");
        regionNumber.add("70");      region.add("QASHQADARYO");
        regionNumber.add("75");      region.add("SURXONDARYO");
        regionNumber.add("80");      region.add("BUXORO");
        regionNumber.add("85");      region.add("NAVOIY");
        regionNumber.add("90");      region.add("XORAZM");
        regionNumber.add("95");      region.add("QORAQALPOG'ISTON");
        for (int i = 0; i < 14; i++) {
            AutoNumber autoNumber = new AutoNumber();
            autoNumber.setId();
            autoNumber.setRegion(region.get(i));
            autoNumber.setLetters(letters);
            autoNumber.setRegionNumber(regionNumber.get(i));
            autoNumber.setNumbers(numbers);
            autoNumber.setInitialPrice(initoalPrice);
            autoNumber.setStatus(Status.SOTUVDA);
            autoNumber.setFullNumber(autoNumber.getRegionNumber()+" "+autoNumber.getNumbers()+" "+autoNumber.getLetters());
            autoNumber.setCategories(Categories.YURIDIK_SHAXS);
            autoNumberService.add(autoNumber);
            changeToPhysical(autoNumber,autoNumberService);
        }
    }

    private static void changeToPhysical(AutoNumber OldAutoNumber, AutoNumberService autoNumberService) {
        AutoNumber autoNumber = new AutoNumber();
        autoNumber.setId();
        autoNumber.setNumbers(OldAutoNumber.getNumbers());
        autoNumber.setLetters(OldAutoNumber.getLetters());
        autoNumber.setRegionNumber(OldAutoNumber.getRegionNumber());
        autoNumber.setRegion(OldAutoNumber.getRegion());
        autoNumber.setInitialPrice(OldAutoNumber.getInitialPrice());
        autoNumber.setStatus(OldAutoNumber.getStatus());
        autoNumber.setCategories(Categories.JISMONIY_SHAXS);
        autoNumber.setFullNumber(autoNumber.getRegionNumber()+" "+autoNumber.getLetters().substring(0,1)+" "+autoNumber.getNumbers()+" "+autoNumber.getLetters().substring(1));
        autoNumberService.add(autoNumber);
    }

    public static void addUsers(UserService userService, CardService cardService){
        User user = new User("DAVRON","+998944001404","1404","davrondavrik3@gmail.com",Categories.JISMONIY_SHAXS,"TOSHKENT VILOYATI");
        user.setId();

        Card card = new Card("8600 0423 8669 2833","04/23", CardFront.getBalance());
        card.setId();
        card.setUserId(user.getId());
        cardService.add(card);

        Card card1 = new Card("8600 3029 2775 4767","10/24", CardFront.getBalance());
        card1.setId();
        card1.setUserId(user.getId());
        cardService.add(card1);
        userService.add(user);

        User user1 = new User("PDP","+998334001404","123456","pdp@gmail.com",Categories.YURIDIK_SHAXS,"TOSHKENT SHAXAR");
        user1.setId();

        Card card2 = new Card("8600 1058 1234 5678","05/22", CardFront.getBalance());
        card2.setId();
        card2.setUserId(user1.getId());
        cardService.add(card2);

        Card card3 = new Card("8600 1234 5432 9874","09/24", CardFront.getBalance());
        card3.setId();
        card3.setUserId(user1.getId());
        cardService.add(card3);
        userService.add(user1);
    }
}
