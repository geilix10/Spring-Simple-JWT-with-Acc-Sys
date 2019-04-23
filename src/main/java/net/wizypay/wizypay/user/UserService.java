package net.wizypay.wizypay.user;

import net.wizypay.wizypay.user.controller.UserController;
import net.wizypay.wizypay.user.model.User;
import net.wizypay.wizypay.utils.model.ResultHolder;
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
