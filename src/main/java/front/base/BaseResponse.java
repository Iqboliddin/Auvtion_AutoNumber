package front.base;

public abstract class BaseResponse {
    public static void response(Boolean status){
        if (status)
            System.out.println("Operatsiya  muvaffaqqiyatli bajarildi");
        else
            System.out.println("Xatolik yuz berdi");
    }
}
