package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.enumerations.Role;
import mk.ukim.finki.wp.lab.model.exceptions.PasswordDoNotMatchException;
import mk.ukim.finki.wp.lab.model.exceptions.UserAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import mk.ukim.finki.wp.lab.service.UserService;
import mk.ukim.finki.wp.lab.service.handler.CustomHandler;
import org.openqa.selenium.InvalidArgumentException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname)
    {
        if (CustomHandler.anyNullOrEmpty(username, password, repeatPassword, name, surname))
        {
            throw new InvalidArgumentException("Some argument is empty or null");
        }

        if (!password.equals(repeatPassword))
        {
            throw new PasswordDoNotMatchException();
        }

        if (this.userRepository.findByUsername(username).isPresent())
        {
            throw new UserAlreadyExistsException();
        }

        return this.userRepository.save(new User(username, this.passwordEncoder.encode(password), name, surname, Role.ROLE_USER));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException(username));
    }
}
