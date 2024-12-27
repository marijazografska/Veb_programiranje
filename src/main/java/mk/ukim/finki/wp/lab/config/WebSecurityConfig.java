package mk.ukim.finki.wp.lab.config;


import mk.ukim.finki.wp.lab.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;


    public WebSecurityConfig(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/songs", "/songs/song-details/**", "/albums", "/filteredSongs", "/auth/register").permitAll()
                        .requestMatchers("/songs/add-form").hasRole("ADMIN")
                        .requestMatchers("/songs/delete/**").hasRole( "ADMIN")
                        .requestMatchers("/songs/edit-form/**").hasAnyRole("MODERATOR", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .permitAll()
                        .failureUrl("/auth/login?error=BadCredentials")
                        .defaultSuccessUrl("/songs")
                )
                .logout(logout-> logout
                        .logoutUrl("/auth/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/songs")

                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/auth/access-denied")
                );


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails admin = User.builder()
                .username("admin1")
                .password(passwordEncoder.encode("admin1"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user1")
                .password(passwordEncoder.encode("user1"))
                .roles("USER")
                .build();

        UserDetails moderator = User.builder()
                .username("moderator1")
                .password(passwordEncoder.encode("moderator1"))
                .roles("MODERATOR")
                .build();

        return new InMemoryUserDetailsManager(admin, user, moderator);
    }

}

