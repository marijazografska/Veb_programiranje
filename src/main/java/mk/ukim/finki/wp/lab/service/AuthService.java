package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.exceptions.PasswordDoNotMatchException;
import mk.ukim.finki.wp.lab.model.exceptions.UserAlreadyExistsException;

public interface AuthService {

    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname) throws PasswordDoNotMatchException, UserAlreadyExistsException;
}
