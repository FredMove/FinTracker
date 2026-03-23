package com.fintracker.fintacker.security;

import com.fintracker.fintacker.Service.UserService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public JwtAuthFilter(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

        System.out.println("JWT Filter triggered, header: " + request.getHeader("Authorization"));
        // заголовок
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7); // чистим, берём сам токен

            if (jwtTokenProvider.validateToken(token)) {
                String username = jwtTokenProvider.getUsername(token);
                UserDetails userDetails = userService.loadUserByUsername(username);

                // успех, пользователь авторизован
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response); // запрос идет дальше
    }
}