package com.zerod.authdemo.security;

import com.google.common.base.Strings;
import com.zerod.authdemo.services.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String token = authorizationHeader.replace("Bearer ", "");
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(JwtTokenService.key)
                .build()
                .parseClaimsJws(token);
        try {
            Claims body = claimsJws.getBody();
            String username = body.getSubject();

            var authorities = (List<Map<String, String>>) body.get("roles");

            Set<SimpleGrantedAuthority> simpleGrantedAuthoritySet = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthoritySet);


            //authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println(authentication);
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
