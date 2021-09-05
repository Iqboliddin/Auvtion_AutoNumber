import front.autoNumber.AutoNumberFront;
import front.card.CardFront;
import front.user.UserFront;
import model.authorization.User;
import model.autoNumber.AutoNumber;
import service.authorization.UserService;
import service.autoNumber.AutoNumberService;
import service.card.CardService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class Main implements Action {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UserService userService = new UserService();
        CardService cardService = new CardService();
        AutoNumberService autoNumberService = new AutoNumberService();
        AutoNumberFront.addNumber(autoNumberService);
        AutoNumberFront.addUsers(userService,cardService);
        autoNumberService.check(userService,cardService);

        int stepCode = 1;
        while (stepCode != 0) {
            System.out.println(action);
            stepCode = scanner.nextInt();
            User newUser = new User();
            switch (stepCode) {
                case 1:
                    numberLists(stepCode, autoNumberService,newUser,cardService,userService);
                    break;
                case 2:
                    numberLists(stepCode, autoNumberService,newUser,cardService,userService);
                    break;
                case 3:
                    UserFront.signUp(userService);
                    break;
                case 4:
                    User user = UserFront.signIn(userService);
                    if (user != null){
                        if (user.getCategories().toString().equals("YURIDIK_SHAXS")){
                            numberLists(2, autoNumberService,user,cardService,userService);
                        }else {
                            numberLists(1, autoNumberService,user,cardService,userService);
                        }
                    }else {
                        System.out.println("Ma`lumotlar xato kiritildi.");
                    }
                    break;
            }

        }


    }

    public static void numberLists(int num, AutoNumberService autoNumberService, User user,CardService cardService,UserService userService) {
        int stepCode1 = 1;
        while (stepCode1 != 0) {

            System.out.println(categories);
            if (user.getCategories() != null){
                System.out.println("6.Balans\n7.Kabinet");
            }
            stepCode1 = scanner.nextInt();
            int index = 0;
            String str = "\n0.Exit\nAvto raqam tartib raqamini kiriting: ";
            switch (stepCode1) {
                case 1:
                    autoNumberService.list();
                    System.out.println(str);
                    index = scanner.nextInt();
                    if (index==0){
                        break;
                    }
                    AutoNumber autoNumber4 = autoNumberService.list(index);
                    UserFront.takeAction(autoNumber4,user,cardService,userService);
                    break;
                case 2:
                    autoNumberService.list(num, BigDecimal.valueOf(50000000));
                    System.out.println(str);
                    index = scanner.nextInt();
                    if (index == 0){
                        break;
                    }else {
                        AutoNumber autoNumber = autoNumberService.list(num, BigDecimal.valueOf(50000000), index);
                        UserFront.takeAction(autoNumber, user,cardService,userService);
                    }
                    break;
                case 3:
                    autoNumberService.list(num, BigDecimal.valueOf(25000000));
                    System.out.println(str);
                    index = scanner.nextInt();
                    if (index == 0){
                        break;
                    }else {
                        AutoNumber autoNumber1 = autoNumberService.list(num, BigDecimal.valueOf(25000000), index);
                        UserFront.takeAction(autoNumber1, user,cardService,userService);
                    }
                    break;
                case 4:
                    autoNumberService.list(num, BigDecimal.valueOf(5000000));
                    System.out.println(str);
                    index = scanner.nextInt();
                    if (index == 0){
                        break;
                    }
                    AutoNumber autoNumber2 = autoNumberService.list(num, BigDecimal.valueOf(5000000), index);
                    UserFront.takeAction(autoNumber2,user,cardService,userService);
                    break;
                case 5:
                    autoNumberService.list(num, BigDecimal.valueOf(1000000));
                    System.out.println(str);
                    index = scanner.nextInt();
                    if (index == 0){
                        break;
                    }
                    AutoNumber autoNumber3 = autoNumberService.list(num, BigDecimal.valueOf(1000000), index);
                    UserFront.takeAction(autoNumber3,user,cardService,userService);
                    break;
                case 6:
                    CardFront.balanceFront(user,cardService);
                    break;
                case 7:
                    autoNumberService.check(userService,cardService);
                    UserFront.userPanel(user,autoNumberService,cardService,userService);
                    break;
                default:break;
            }
        }
    }
}
