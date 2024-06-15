package clinic.config;


import clinic.serviecs.CustomMangerConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests) ->
                        requests.requestMatchers(HttpMethod.GET, "/", "/mangerLogin", "/login")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/", "/mangerLogin")
                                .permitAll()
                                .requestMatchers("/login", "/my-logout")
                                .authenticated()
                                .requestMatchers("/mangerDashboard/**")
                                .hasRole("MANAGER")
                                .requestMatchers("/employeeDashboard/**")
                                .hasRole("EMPLOYEE")
                                .anyRequest().authenticated())
                .formLogin((form) ->
                        form.loginPage("/mangerLogin")
                                .defaultSuccessUrl("/mangerDashboard")
                                .loginProcessingUrl("/mangerLogin")
                                .failureUrl("/mangerLogin?error=true")
                                .successHandler((request, response, authentication) -> {
                                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                                    final String[] role = {""};
                                    userDetails.getAuthorities().forEach((auth) -> role[0] += auth.getAuthority());

                                    response.sendRedirect("/login?role=" + role[0]);
                                })
                                .permitAll())

                .logout((logout) -> logout
                        .permitAll()
                        .logoutUrl("/my-logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());


        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    private final PasswordEncoder passwordEncoder;
    private final CustomMangerConfigService customMangerConfigService;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomMangerConfigService customMangerConfigService) {
        this.passwordEncoder = passwordEncoder;
        this.customMangerConfigService = customMangerConfigService;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customMangerConfigService).passwordEncoder(passwordEncoder);
    }


}
