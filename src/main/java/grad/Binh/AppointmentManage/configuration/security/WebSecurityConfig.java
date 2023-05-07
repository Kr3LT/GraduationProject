package grad.Binh.AppointmentManage.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final CustomUserDetailService customUserDetailsService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(CustomUserDetailService customUserDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/").hasAnyRole("CUSTOMER", "PROVIDER", "ADMIN")
                .requestMatchers("/api/**").hasAnyRole("CUSTOMER", "PROVIDER", "ADMIN")
                .requestMatchers("/customers/all").hasRole("ADMIN")
                .requestMatchers("/providers/new").hasRole("ADMIN")
                .requestMatchers("/invoices/all").hasRole("ADMIN")
                .requestMatchers("/providers/all").hasRole("ADMIN")
                .requestMatchers("/customers/**").hasAnyRole("CUSTOMER", "ADMIN")
                .requestMatchers("/providers/availability/**").hasRole("PROVIDER")
                .requestMatchers("/providers/**").hasAnyRole("PROVIDER", "ADMIN")
                .requestMatchers("/works/**").hasRole("ADMIN")
                .requestMatchers("/exchange/**").hasRole("CUSTOMER")
                .requestMatchers("/appointments/new/**").hasRole("CUSTOMER")
                .requestMatchers("/appointments/**").hasAnyRole("CUSTOMER", "PROVIDER", "ADMIN")
                .requestMatchers("/invoices/**").hasAnyRole("CUSTOMER", "PROVIDER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout().logoutUrl("/perform_logout")
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(customUserDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web->  web.ignoring()
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico", "/webjars/**")
                .requestMatchers("/customers/new/**");
    }

}
