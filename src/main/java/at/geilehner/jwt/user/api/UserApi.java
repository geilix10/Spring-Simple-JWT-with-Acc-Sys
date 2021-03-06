package at.geilehner.jwt.user.api;


import at.geilehner.jwt.user.UserService;
import at.geilehner.jwt.user.model.SignInBody;
import at.geilehner.jwt.user.model.User;
import at.geilehner.jwt.utils.configuration.ValidationErrorBuilder;
import at.geilehner.jwt.utils.model.ResultHolder;
import at.geilehner.jwt.utils.configuration.ValidationError;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserApi {
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signin")
    public ResultHolder login(@RequestBody SignInBody signInBody) {
        return userService.signin(signInBody.getEmail(), signInBody.getPassword());
    }

    @PostMapping("/signup")
    public ResultHolder signup(@Valid @RequestBody User user) {
        return userService.signup(user);
    }

    @GetMapping(value = "/info")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResultHolder userInfo(HttpServletRequest req) {
        return new ResultHolder(true,"info",userService.whoami(req));
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return createValidationError(exception);
    }

    private ValidationError createValidationError(MethodArgumentNotValidException e) {
        return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
