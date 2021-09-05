package front.user;

import front.base.BaseResponse;
import model.authorization.User;
import model.autoNumber.AutoNumber;
import model.autoNumber.Categories;
import model.autoNumber.NewPrice;
import model.card.Card;
import service.authorization.UserService;
import service.autoNumber.AutoNumberService;
import service.card.CardService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class UserFront extends BaseResponse {
    static Date date = new Date(System.currentTimeMillis());

    static Scanner scanner = new Scanner(System.in);

    public static void signUp(UserService userService){
        System.out.println("1.Jismoniy shaxs     2.Yuridik shaxs");
        int stepCode = scanner.nextInt();
        switch (stepCode){
            case 1:
                signUpForPhysical(userService);
                break;
            case 2:
                signUpForJuridical(userService);
                break;
            default:
                System.out.println("Noto`g`ri raqam kiritdingiz\n");
        }
    }

    public static void signUpForPhysical(UserService userService){
        User user = new User();
        scanner = new Scanner(System.in);
        user.setId();
        System.out.print("Ismingizni kiriting: ");
        user.setName(scanner.nextLine());

        System.out.print("Telefon raqamingiz: ");
        user.setPhoneNumber(scanner.nextLine());

        System.out.print("E-mail: ");
        user.setEmail(scanner.nextLine());

        System.out.print("Shaxar yoki viloyat nomi: ");
        user.setRegion(scanner.nextLine());

        System.out.print("Parol o`rnating: ");
        user.setPassword(scanner.nextLine());

        user.setCategories(Categories.JISMONIY_SHAXS);

        boolean add = userService.add(user);
        response(add);
    }

    public static void signUpForJuridical(UserService userService){
        User user = new User();
        scanner = new Scanner(System.in);
        user.setId();
        System.out.print("Ismingizni kiriting: ");
        user.setName(scanner.nextLine());

        System.out.print("Telefon raqamingiz: ");
        user.setPhoneNumber(scanner.nextLine());

        System.out.print("E-mail: ");
        user.setEmail(scanner.nextLine());

        System.out.print("Shaxar yoki viloyat nomi: ");
        user.setRegion(scanner.nextLine());

        System.out.print("Parol o`rnating: ");
        user.setPassword(scanner.nextLine());
        user.setCategories(Categories.YURIDIK_SHAXS);

        boolean add = userService.add(user);
        response(add);
    }

    public static User signIn(UserService userService){
        scanner = new Scanner(System.in);
        System.out.println("Telefon raqamingiz: ");
        String phoneNumber = scanner.nextLine();
        scanner = new Scanner(System.in);

        System.out.println("Parol: ");
        String password = scanner.nextLine();

        return userService.getUser(phoneNumber, password);
    }

    public static void takeAction(AutoNumber autoNumber, User user, CardService cardService,UserService userService){
        System.out.println("\n"+autoNumber.getFullNumber());
        System.out.println("Boshlang`ich narxi: " + autoNumber.getInitialPrice().toBigInteger().intValueExact());
        String currentPrice = "";
        if (autoNumber.getNewPrices().size()>0){
            currentPrice = ""+autoNumber.getNewPrices().get(autoNumber.getNewPrices().size() - 1).getNewPrice().toBigInteger().intValueExact();
        }else {
            currentPrice = "Narx taklif etilmadi";
        }
        System.out.println("Joriy narx: "+currentPrice);
        System.out.println("Keyingi eng yaxshi narx: "+autoNumber.getNextPossiblePrice().toBigInteger().intValueExact());
        System.out.println("Region: " + autoNumber.getRegion());
        System.out.println("Kategoriya: " + autoNumber.getCategories().toString());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        System.out.println("Tugash vaqti: "+formatter.format(autoNumber.getEndDate()));
        System.out.println("\n1.Narx taklif etish       2.Tanlanganlarga qo`shish.");
        scanner = new Scanner(System.in);
        int stepCode = scanner.nextInt();
        switch (stepCode){
            case 1:
                BigInteger balance = cardService.returnBalance(user);
                if (balance.compareTo(autoNumber.getNextPossiblePrice().toBigInteger()) >= 0) {
                    NewPrice newPrice = new NewPrice();
                    newPrice.setNewPrice(autoNumber.getNextPossiblePrice());
                    newPrice.setDateOfPrice(date);
                    newPrice.setUserId(user.getId());
                    autoNumber.getNewPrices().add(newPrice);
                    response(true);
                }else {
                    if (user.getPhoneNumber() == null){
                        System.out.println("Auksionda qatnashish uchun avval ro`yxatdan o`tish kerak.\n0.Exit\n1.Ro`yxatdan o`tish");
                        scanner = new Scanner(System.in);
                        int stepCode1 = scanner.nextInt();
                        if (stepCode1 == 1){
                            UserFront.signUp(userService);
                        }
                    }else {
                        System.out.println("Xisobingizda mablag` yetarli emas iltimos xisobingizni to`ldiring");
                    }
                    }
                break;
            case 2:
                if (user.getPhoneNumber() == null){
                    System.out.println("Avval ro`yxatdan o`tish kerak.\n0.Exit\n1.Ro`yxatdan o`tish");
                    scanner = new Scanner(System.in);
                    int stepCode1 = scanner.nextInt();
                    if (stepCode1 == 1){
                        UserFront.signUp(userService);
                        UserFront.signIn(userService);
                    }
                }else {
                    UUID id = autoNumber.getId();
                    boolean ans = false;
                    for (UUID uuid : user.getFavourites()) {
                        if (uuid.equals(id)) {
                            ans = true;
                        }
                    }
                    if (!ans) {
                        user.getFavourites().add(id);
                    }
                    user.getFavourites().add(id);
                    response(!ans);
                    break;
                }
            default:
        }
    }

    public static void userPanel(User user, AutoNumberService autoNumberService,CardService cardService,UserService userService){
        System.out.println("0.Exit\n1.Shaxsiy ma`lumotlar\n2.Tanlangan raqamlar\n3.Sotib olingan raqamlar");
    int stepCode = scanner.nextInt();
    switch (stepCode){
        case 1:
            System.out.println(user.getName());
            System.out.println(user.getPhoneNumber());
            System.out.println(user.getEmail());
            System.out.println(user.getRegion());
            System.out.println("\n");
            break;
        case 2:
            favouritesList(user,autoNumberService,cardService,userService);
            break;
        case 3:
            boughtNumbersList(user);
            break;
        default:break;
    }
    }
    public static void boughtNumbersList(User user){
        int count = 0;
        if (user.getBoughtNumbers().size()>0) {
            for (AutoNumber autoNumber : user.getBoughtNumbers()) {
                System.out.println("----------------" + ++count + "----------------");
                System.out.println(autoNumber.getFullNumber());
                System.out.println("Region: " + autoNumber.getRegion());
                System.out.println("Kategoriya: " + autoNumber.getCategories().toString());
                System.out.println("Sotib ilingan narxi: " + autoNumber.getNewPrices().get(autoNumber.getNewPrices().size() - 1).getNewPrice().toBigInteger().toString());
            }
            System.out.println("\n");
        }else {
            System.out.println("Siz xali raqam sotib olganingiz yo`q.");
        }
    }
    public static void favouritesList(User user, AutoNumberService autoNumberService,CardService cardService,UserService userService){
        if (user.getFavourites().size()>0) {
            for (UUID id : user.getFavourites()) {
                autoNumberService.list(id);
            }
            System.out.println("Avto raqam tartib raqamini kiriting: ");
            int stepCode = scanner.nextInt();
            if (stepCode<=user.getFavourites().size()){
                UUID uuid = user.getFavourites().get(stepCode - 1);
                AutoNumber autoNumber = autoNumberService.findAutoNumber(uuid);
                takeAction(autoNumber,user,cardService,userService);
            }
        }else {
            System.out.println("Sizda tanlangan raqamlar mavjud emas.\n");
        }
    }
}
