package com.minimal.blog_api.config;

import com.minimal.blog_api.dto.PostReqRes;
import com.minimal.blog_api.service.BlogUserDetailsService;
import com.minimal.blog_api.service.BlogUserManagementService;
import com.minimal.blog_api.service.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Getter
    @Setter
    private String LoggedInUserEmail;

    @Autowired
    private BlogUserDetailsService blogUserDetailsService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            final String jwtToken;
            final String username;
            if(authHeader == null || authHeader.isBlank()){
                filterChain.doFilter(request,response);
                return;
            }
            jwtToken = authHeader.substring(7);
            username = jwtUtils.extractUsername(jwtToken);
            setLoggedInUserEmail(username);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = blogUserDetailsService.loadUserByUsername(username);

                if(jwtUtils.isTokenValid(jwtToken,userDetails)){
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    securityContext.setAuthentication(token);

                    SecurityContextHolder.setContext(securityContext);
                }

            }


        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }

        filterChain.doFilter(request,response);
    }


}
