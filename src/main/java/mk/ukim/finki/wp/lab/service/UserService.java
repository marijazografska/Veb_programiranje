package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.enumerations.Role;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface UserService extends UserDetailsService
{
    User register(String username,String password, String repeatPassword, String name, String surname);
}