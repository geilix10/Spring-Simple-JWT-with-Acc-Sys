package net.wizypay.wizypay.user.controller;

import net.wizypay.wizypay.security.JwtTokenProvider;
import net.wizypay.wizypay.user.model.Role;
import net.wizypay.wizypay.user.model.User;
import net.wizypay.wizypay.user.repository.UserRepository;
import net.wizypay.wizypay.utils.model.ResultHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Component
public class UserController {
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResultHolder signin(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            User u = userRepository.findByEmail(email);
            return new ResultHolder(true,"logged in!",jwtTokenProvider.createToken(email, u.getRoles()));

        } catch (AuthenticationException e) {
            return new ResultHolder(false, "Invalid email/password supplied");
        }
    }

    public ResultHolder signup(User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            List<Role> roles = new ArrayList<>();
            roles.add(Role.ROLE_CLIENT);
            user.setRoles(roles);
            user.setId(null);
            userRepository.save(user);
            return new ResultHolder(true,"Registered!",jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
        } else {
            return new ResultHolder(false, "Email is already in use!");
        }
    }

    public User getUser(HttpServletRequest req) {
        return userRepository.findByEmail(jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(req)));
    }

    public User getActualUser() {
        return userRepository.findByEmail(getActualEmail());
    }

    public String getActualEmail() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
