package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.lab.model.exceptions.PasswordDoNotMatchException;
import mk.ukim.finki.wp.lab.model.exceptions.UserAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import mk.ukim.finki.wp.lab.service.AuthService;
import mk.ukim.finki.wp.lab.service.handler.CustomHandler;
import org.openqa.selenium.InvalidArgumentException;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    @Override
    public User login(String username, String password) {
        if (CustomHandler.isNullOrEmpty(username) || CustomHandler.isNullOrEmpty(password))
        {
            throw new InvalidArgumentException("Username or password is empty or null");
        }

        return this.userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) throws PasswordDoNotMatchException, UserAlreadyExistsException {
        return null;
    }
}
