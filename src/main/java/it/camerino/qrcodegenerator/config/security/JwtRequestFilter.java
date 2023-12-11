//package it.camerino.qrcodegenerator.config.security;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
////    @Override
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
////            throws ServletException, IOException {
////
////    }
//
//    @Override
//    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//        String email = null;
//        String jwt = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            email = jwtUtil.extractEmail(jwt);
//        }
//
//        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.myUserServiceImpl.loadUserByUsername(email);
//            if (jwtUtil.validateToken(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
