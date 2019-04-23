package at.geilehner.jwt.user;

import at.geilehner.jwt.user.model.User;
import at.geilehner.jwt.utils.model.ResultHolder;
import at.geilehner.jwt.user.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserService {
    private UserController userController;

    public ResultHolder signin(String username, String password) {
        return userController.signin(username, password);
    }

    public ResultHolder signup(User user) {
        return userController.signup(user);
    }

    public User whoami(HttpServletRequest req) {
        return userController.getUser(req);
    }

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }
}
