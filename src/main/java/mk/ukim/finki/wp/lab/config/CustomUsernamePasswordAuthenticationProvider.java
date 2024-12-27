package mk.ukim.finki.wp.lab.config;

import mk.ukim.finki.wp.lab.service.UserService;

import mk.ukim.finki.wp.lab.service.handler.CustomHandler;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider
    {
        private final UserService service;
        private final PasswordEncoder passwordEncoder;

        public CustomUsernamePasswordAuthenticationProvider(UserService service, PasswordEncoder passwordEncoder)
        {
            this.service = service;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException
        {
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();

            if (CustomHandler.anyNullOrEmpty(username, password))
            {
                throw new BadCredentialsException("Empty Credentials");
            }

            UserDetails userDetails = this.service.loadUserByUsername(username);

            if (!passwordEncoder.matches(password, userDetails.getPassword()))
            {
                throw new BadCredentialsException("Password is incorrect");
            }

            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        }

        @Override
        public boolean supports(Class<?> authentication)
        {
            return authentication.equals(UsernamePasswordAuthenticationToken.class);
        }
}
