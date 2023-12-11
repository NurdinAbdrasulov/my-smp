package it.camerino.qrcodegenerator.config.security;

import it.camerino.qrcodegenerator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigCopy extends WebSecurityConfigurerAdapter {

    final UserService userService;

    @Autowired
    public SecurityConfigCopy(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/test/**", "/test").permitAll() // Allow public endpoints
                .anyRequest().authenticated();
//                .and()
//                .addFilterBefore(firebaseAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Add Firebase authentication filter
    }

    @Bean
    public FirebaseAuthenticationProvider firebaseAuthenticationProvider() {
        return new FirebaseAuthenticationProvider(userService);
    }

//    @Bean
//    public FirebaseAuthenticationFilter firebaseAuthenticationFilter() throws Exception {
//        FirebaseAuthenticationFilter filter = new FirebaseAuthenticationFilter("/secure/**");
//        filter.setAuthenticationManager(authenticationManagerBean());
//        return filter;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(firebaseAuthenticationProvider());
    }

//    @Bean
//    public ProviderManager authenticationManager() {
//        return new ProviderManager(Collections.singletonList(firebaseAuthenticationProvider()));
//    }
}
