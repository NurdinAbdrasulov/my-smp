package it.camerino.qrcodegenerator.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class FirebaseAuthenticationFilter extends AbstractAuthenticationProcessingFilter /*implements Filter*/ {

    public FirebaseAuthenticationFilter() {
        super(new AntPathRequestMatcher("/**"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String idToken = extractTokenFromRequest(request);

        if (idToken != null) {
            return getAuthenticationManager().authenticate(new FirebaseAuthenticationToken(idToken));
        }

        return null;
    }


    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }


//    @Override
//    protected void successfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain,
//            Authentication authResult) throws IOException {
//        super.successfulAuthentication(request, response, chain, authResult);
//        chain.doFilter(request, response);
//    }

//    @Override
//    protected void successfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain,
//            Authentication authResult) throws IOException {
//        super.successfulAuthentication(request, response, chain, authResult);
//        chain.doFilter(request, response);
//    }

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        return null;
//    }
}
