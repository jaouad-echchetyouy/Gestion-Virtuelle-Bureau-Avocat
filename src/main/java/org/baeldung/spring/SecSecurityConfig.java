package org.baeldung.spring;

import org.baeldung.persistence.dao.UserRepository;
import org.baeldung.security.CustomRememberMeServices;
import org.baeldung.security.google2fa.CustomAuthenticationProvider;
import org.baeldung.security.google2fa.CustomWebAuthenticationDetailsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

@Configuration
@ComponentScan(basePackages = { "org.baeldung.security" })
@EnableWebSecurity
public class SecSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private LogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomWebAuthenticationDetailsSource authenticationDetailsSource;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/resources/**", "/webjars/**", "/email_templates/**",
                        "/img/**", "/images/**", "/contactform/**", "/fonts/**", "/ico/**",
                        "/css/**", "/vendor/**", "/js/**", "/assets/**", "/node_modules/**",
                        "/wp-conten/**", "/wp-includes/**", "/registration/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Admin/**").hasAuthority("ADMIN_PRIVILEGE")
                        .requestMatchers("/Secretaire/**").hasAuthority("SECRETAIRE_PRIVILEGE")
                        .requestMatchers("/Avocat/**").hasAuthority("AVOCAT_PRIVILEGE")
                        .requestMatchers("/super_admin/**").hasAuthority("USER_PRIVILEGE")
                        .requestMatchers("/Client/**").hasAuthority("CLIENT_PRIVILEGE")
                        .requestMatchers("/portfolio", "/services", "/about-us", "/contact-us", "/index", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/Client/index", true)
                        .failureUrl("/login?error=true")
                        .successHandler(myAuthenticationSuccessHandler)
                        .failureHandler(authenticationFailureHandler)
                        .authenticationDetailsSource(authenticationDetailsSource)
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .invalidSessionUrl("/invalidSession.html")
                        .maximumSessions(1).sessionRegistry(sessionRegistry())
                        .and()
                        .sessionFixation().none()
                )
                .logout(logout -> logout
                        .logoutSuccessHandler(myLogoutSuccessHandler)
                        .invalidateHttpSession(false)
                        .logoutSuccessUrl("/logout.html?logSucc=true")
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                        .rememberMeServices(rememberMeServices())
                        .key("theKey")
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider())
                .build();
    }

    // Beans

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        CustomRememberMeServices rememberMeServices = new CustomRememberMeServices("theKey", userDetailsService, new InMemoryTokenRepositoryImpl());
        return rememberMeServices;
    }
}