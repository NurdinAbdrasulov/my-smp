//package it.camerino.qrcodegenerator.config.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/test/**", "/test").permitAll() // Allow public endpoints
//                .anyRequest().authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//    }
//
////    @Bean
////    public FirebaseAuthenticationProvider firebaseAuthenticationProvider() {
////        return new FirebaseAuthenticationProvider(null);
////    }
////
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) {
////        auth.authenticationProvider(firebaseAuthenticationProvider());
////    }
//
////    @Bean
////    public ProviderManager authenticationManager() {
////        return new ProviderManager(Collections.singletonList(firebaseAuthenticationProvider()));
////    }
//}
