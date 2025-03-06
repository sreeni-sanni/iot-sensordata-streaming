package com.iot.streamingdataapi.auth.filter;

import com.iot.streamingdataapi.auth.service.JWTAuthenticationProvider;
import com.iot.streamingdataapi.auth.service.JwtUtilService;
import com.iot.streamingdataapi.exception.JwtException;
import com.iot.streamingdataapi.exception.SensorDataApiException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

import static com.iot.streamingdataapi.constants.Constants.*;

@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private  JwtUtilService jwtUtilService;
    private  JWTAuthenticationProvider provider;
    private final HandlerExceptionResolver resolver;

    public JwtAuthFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver, JwtUtilService jwtUtilService, JWTAuthenticationProvider provider) {
        this.resolver = resolver;
        this.jwtUtilService = jwtUtilService;
        this.provider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("URL: {}", request.getRequestURI());
        if ((request.getRequestURI().startsWith("/api/v1/auth/token") ||
                request.getRequestURI().startsWith("/actuator/health") ||
                request.getRequestURI().startsWith(SWAGGER_UI) ||
                request.getRequestURI().startsWith(SWAGGER_V3_API))) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = getTokenFromRequest(request);
            try {
                if (StringUtils.hasText(token) && jwtUtilService.validateToken(token)) {
                    Authentication authentication = provider.authenticate(new UsernamePasswordAuthenticationToken(token, token));
                    if (authentication != null) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (SignatureException | ExpiredJwtException exe) {
                if (exe instanceof SignatureException) {
                    throw new JwtException(INVALID_JWT_TOKEN);
                } else {
                    throw new JwtException(TOKEN_EXPIRED);
                }
            }
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            resolver.resolveException(request, response, null, e);
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(7);
        }
        throw new SensorDataApiException(TOKEN_MISSING);
    }
}
