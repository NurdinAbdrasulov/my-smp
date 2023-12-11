package it.camerino.qrcodegenerator.config.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FirebaseAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final UserDetailsService userDetailsService;

    public FirebaseAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // No additional checks needed
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        FirebaseToken firebaseToken = verifyFirebaseToken(authentication.getCredentials().toString());

        if (firebaseToken == null) {
            throw new UsernameNotFoundException("Invalid Firebase token");
        }

        // Load user details from your database or user service
        UserDetails userDetails = userDetailsService.loadUserByUsername(firebaseToken.getUid());

        return userDetails;
    }

    private FirebaseToken verifyFirebaseToken(String idToken) {
        try {
            return FirebaseAuth.getInstance().verifyIdToken(idToken);
        } catch (FirebaseAuthException e) {
            // Handle verification failure
            return null;
        }
    }

//    @Override
//    protected AbstractAuthenticationToken createSuccessAuthentication(Object principal, Authentication authentication, UserDetails userDetails) {
//        // You may customize the authentication token if needed
//        return super.createSuccessAuthentication(principal, authentication, userDetails);
//    }

    @Override
    protected AbstractAuthenticationToken createSuccessAuthentication(Object principal, Authentication authentication, UserDetails userDetails) {
        if (authentication instanceof AbstractAuthenticationToken) {
            return (AbstractAuthenticationToken) authentication;
        } else {
            // Create a new AbstractAuthenticationToken or use another appropriate token class
            return new UsernamePasswordAuthenticationToken(principal, null, userDetails.getAuthorities());
        }
    }

}

