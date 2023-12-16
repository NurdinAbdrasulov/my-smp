package it.camerino.qrcodegenerator.config.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import it.camerino.qrcodegenerator.entity.User;
import it.camerino.qrcodegenerator.exception.BaseException;
import it.camerino.qrcodegenerator.service.impl.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    final OAuth2ResourceServerProperties oAuth2ResourceServerProperties;
    static final String[] WHITE_LIST_ENDPOINT = {
            "/qr/redirect",
            "/test/**",
            "/user/register"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req ->
                                req.requestMatchers(WHITE_LIST_ENDPOINT)
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider());

        http.oauth2ResourceServer(oauth2ResourceServer ->
                oauth2ResourceServer
                        .jwt(jwt -> jwt.decoder(jwtDecoder(oAuth2ResourceServerProperties)))
        );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {//todo check
        return firebaseToken -> {
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
                UserRecord userByEmail = FirebaseAuth.getInstance().getUserByEmail(decodedToken.getEmail());

                return new MyUserDetails(User.builder()
                        .username(userByEmail.getEmail())
                        .build()
                );
            } catch (FirebaseAuthException e) {
                throw new BaseException("Auth custom exception", HttpStatus.BAD_REQUEST);
            }
        };
    }

    @Bean
    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        return NimbusJwtDecoder.withJwkSetUri(
                properties.getJwt().getJwkSetUri()).build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
