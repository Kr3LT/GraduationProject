package grad.Binh.AppointmentManage.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
    private String RoleCustomer = "CUSTOMER";
    private String RoleProvider = "PROVIDER";
    private String RoleAdmin = "ADMIN";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authenticationProvider(authenticationProvider())
//                  .authorizeHttpRequests().anyRequest().permitAll()
                .authorizeHttpRequests()
                .requestMatchers("/").hasAnyRole(RoleCustomer, RoleProvider, RoleAdmin)
                .requestMatchers("/api/**").hasAnyRole(RoleCustomer, RoleProvider, RoleAdmin)
                .requestMatchers("/customers/all").hasRole(RoleAdmin)
                .requestMatchers("/providers/new").hasRole(RoleAdmin)
                .requestMatchers("/invoices/all").hasRole(RoleAdmin)
                .requestMatchers("/providers/all").hasRole(RoleAdmin)
                .requestMatchers("/customers/**").hasAnyRole(RoleCustomer, RoleAdmin)
                .requestMatchers("/providers/availability/**").hasRole(RoleProvider)
                .requestMatchers("/providers/**").hasAnyRole(RoleProvider, RoleAdmin)
                .requestMatchers("/works/**").hasRole(RoleAdmin)
                .requestMatchers("/exchange/**").hasRole(RoleCustomer)
                .requestMatchers("/appointments/new/**").hasRole(RoleCustomer)
                .requestMatchers("/appointments/**").hasAnyRole(RoleCustomer, RoleProvider, RoleAdmin)
                .requestMatchers("/invoices/**").hasAnyRole(RoleCustomer, RoleProvider, RoleAdmin)
                .anyRequest().authenticated()
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
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "favicon.ico", "/webjars/**")
                .requestMatchers("/customers/new/**");
    }

}
