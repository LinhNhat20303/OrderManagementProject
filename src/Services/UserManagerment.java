package Services;

import Main.UserRole;
import java.util.ArrayList;
import java.util.List;
import model.User;
import utils.Util;

public class UserManagerment {

    private static final UserManagerment instance = new UserManagerment();

    private User currentUser;

    public static UserManagerment getInstance() {
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<User> getUserList() {
        //////////////////////////////////// ,,,,,,,,
        List<User> userList = new ArrayList();
        userList.add(new User("admin", "123", UserRole.ADMIN));
        userList.add(new User("user", "123", UserRole.USER));
        ////////////////////////////////////

        return userList;
    }

    private UserManagerment() {
        currentUser = null;
    }

    public boolean login() {
        System.out.println("Login ...");
        String name = Util.inputString("user name", false);
        String pass = Util.inputString("pass", false);
        this.currentUser = validate(name, pass);
        return this.currentUser != null;
    }

    private User validate(String name, String pass) {
        if (name != null && pass != null) {
            List<User> userList = UserManagerment.getInstance().getUserList();
            if (userList != null) {
                for (User user : userList) {
                    if (name.equals(user.getId()) && pass.equals(user.getPass())) {
                        return user;
                    }
                }
            }
        }
        return null;
    }
}
