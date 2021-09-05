package service.autoNumber;

import model.authorization.User;
import model.autoNumber.AutoNumber;
import model.autoNumber.Status;
import model.card.Card;
import service.authorization.UserService;
import service.base.BaseService;
import service.card.CardService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

public class AutoNumberService implements BaseService {
    Date date = new Date(System.currentTimeMillis());

    ArrayList<AutoNumber> autoNumbers = new ArrayList<>();

    @Override
    public boolean add(Object object) {
        AutoNumber autoNumber = (AutoNumber) object;
        boolean isSuccess = false;
        if (!autoNumbers.contains(autoNumber)) {
            autoNumbers.add(autoNumber);
            isSuccess = true;
        }
        return isSuccess;
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
    public void list() {
        int count = 0;
        for (AutoNumber autoNumber : this.autoNumbers) {
            if (autoNumber.getStatus().equals(Status.SOTUVDA)) {
                System.out.println("--------------------   " + (++count) + "   --------------------");
                System.out.println(autoNumber.getFullNumber());
                System.out.println("Boshlang`ich narxi: " + autoNumber.getInitialPrice().toBigInteger().intValueExact());
                System.out.println("Region: " + autoNumber.getRegion());
                System.out.println("Kategoriya: " + autoNumber.getCategories().toString());
            }
        }
    }

    public AutoNumber list(int choosenNumber) {
        int count = 0;
        for (AutoNumber autoNumber : this.autoNumbers) {
            if (autoNumber.getStatus().equals(Status.SOTUVDA)) {
                count++;
                if (count == choosenNumber) {
                    return autoNumber;
                }
            }
        }
        return null;
    }

    public void list(UUID id) {
        int count = 0;
        for (AutoNumber autoNumber : autoNumbers) {
            if (autoNumber.getId().equals(id)) {
                System.out.println("--------------------   " + (++count) + "   --------------------");
                System.out.println(autoNumber.getFullNumber());
                System.out.println("Boshlang`ich narxi: " + autoNumber.getInitialPrice().toBigInteger().intValueExact());
                System.out.println("Region: " + autoNumber.getRegion());
                System.out.println("Kategoriyaa: " + autoNumber.getCategories().toString());
                System.out.println("\n");
            }
        }
    }

    public AutoNumber findAutoNumber(UUID id) {
        int count = 0;
        for (AutoNumber autoNumber : autoNumbers) {
            if (autoNumber.getId().equals(id)) {
                return autoNumber;
            }
        }
        return null;
    }

    public void list(int stepCode, BigDecimal initialPrice) {
        String str = "";
        if (stepCode == 1) {
            str = "JISMONIY_SHAXS";
        } else {
            str = "YURIDIK_SHAXS";
        }
        int count = 0;
        for (AutoNumber autoNumber : this.autoNumbers) {
            if (autoNumber.getStatus().equals(Status.SOTUVDA)) {
                if (autoNumber.getCategories().toString().equals(str) && autoNumber.getInitialPrice().compareTo(initialPrice) == 0) {
                    System.out.println("--------------------   " + (++count) + "   --------------------");
                    System.out.println(autoNumber.getFullNumber());
                    System.out.println("Boshlang`ich narxi: " + autoNumber.getInitialPrice().toBigInteger().intValueExact());
                    System.out.println("Region: " + autoNumber.getRegion());
                    System.out.println("Kategoriya: " + autoNumber.getCategories().toString());
                }
            }
        }
    }

    public AutoNumber list(int stepCode, BigDecimal initialPrice, int choosenNumber) {
        String str = "";
        if (stepCode == 1) {
            str = "JISMONIY_SHAXS";
        } else {
            str = "YURIDIK_SHAXS";
        }
        int count = 0;
        for (AutoNumber autoNumber : this.autoNumbers) {
            if (autoNumber.getStatus().equals(Status.SOTUVDA)) {
                if (autoNumber.getCategories().toString().equals(str) && autoNumber.getInitialPrice().compareTo(initialPrice) == 0) {
                    count++;
                    if (count == choosenNumber) {
                        return autoNumber;
                    }
                }
            }
        }
        return null;
    }

    public AutoNumber search(String autoNumber) {
        for (AutoNumber autoNumber1 : autoNumbers) {
            if (autoNumber.equals(autoNumber1.getFullNumber())) {
                return autoNumber1;
            }
        }
        return null;
    }

    public void check(UserService userService, CardService cardService) {
        Date date = new Date(System.currentTimeMillis());
        for (AutoNumber autoNumber : autoNumbers) {
            if (autoNumber.getStatus().equals(Status.SOTUVDA)){
                if (autoNumber.getNewPrices().size() > 0) {
                    Date dateOfPrice = autoNumber.getNewPrices().get(autoNumber.getNewPrices().size() - 1).getDateOfPrice();
                    dateOfPrice.setTime(dateOfPrice.getTime() + 1800);

                    long l = date.getTime() - dateOfPrice.getTime();
                    if (date.getTime() > dateOfPrice.getTime()) {
                        autoNumber.setStatus(Status.SOTILGAN);
                        UUID userId = autoNumber.getNewPrices().get(autoNumber.getNewPrices().size() - 1).getUserId();
                        User user = userService.getUser(userId);
                        BigDecimal price = autoNumber.getNewPrices().get(autoNumber.getNewPrices().size() - 1).getNewPrice();
                        for (Card card : cardService.getCards()) {
                            if (card.getUserId().equals(user.getId())) {
                                if (card.getBalance().compareTo(price) >= 0) {
                                    card.setBalance(card.getBalance().subtract(price));
                                    price = BigDecimal.valueOf(0);
                                } else {
                                    price.subtract(card.getBalance());
                                    card.setBalance(BigDecimal.valueOf(0));
                                }
                            }
                            if (price.compareTo(BigDecimal.valueOf(0)) == 0) {
                                break;
                            }
                        }
                        boolean ans = false;
                        for (AutoNumber autoNumber1 : user.getBoughtNumbers()) {
                            if (autoNumber.getFullNumber().equals(autoNumber1.getFullNumber())) {
                                ans = true;
                            }
                        }
                        if (!ans) {
                            user.getBoughtNumbers().add(autoNumber);
                        }
                    }
                }
        }
        }

    }
}
