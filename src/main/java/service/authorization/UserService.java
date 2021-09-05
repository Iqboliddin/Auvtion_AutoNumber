package service.authorization;

import model.authorization.User;
import service.base.BaseService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class UserService implements BaseService {
    private ArrayList<User> users = new ArrayList<>();

    @Override
    public boolean add(Object object) {
        User newUser = (User) object;
        boolean ans = false;
        for (User user : users) {
            if (user.getPhoneNumber().equals(newUser.getPhoneNumber())){
                ans = true;
                break;
            }
        }
        if (!ans){
            users.add(newUser);
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
    public void list() {

    }

    public User getUser(String phoneNumber, String password){
        User user1 = null;
        for (User user:users) {
            if (user.getPhoneNumber().equals(phoneNumber) && user.getPassword().equals(password)){
                user1 = user;
                break;
            }
        }
        return user1;
    }

    public  User getUser(UUID id){
        for (User user:users) {
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
}
