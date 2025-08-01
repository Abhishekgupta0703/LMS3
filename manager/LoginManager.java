package manager;

import java.util.ArrayList;
import model.User;

public class LoginManager {

    public User userLogin(String username, String password, ArrayList<User> users) {
        for (User u : users) {
            if (u.getUserName().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}
