package com.example.proveedores.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Valida el header X-API-KEY antes de permitir el acceso a los endpoints.
 * Si falta o es incorrecta, responde 401 UNAUTHORIZED.
 */
@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    public static final String API_KEY_HEADER = "X-API-KEY";

    private final String apiKeyEsperada;

    public ApiKeyFilter(@Value("${app.security.api-key}") String apiKeyEsperada) {
        this.apiKeyEsperada = apiKeyEsperada;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String apiKey = request.getHeader(API_KEY_HEADER);

        if (apiKey == null || !esIgual(apiKey, apiKeyEsperada)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write(
                    "{\"status\":401,\"error\":\"Unauthorized\",\"mensaje\":\"API Key invalida o ausente\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

    // Comparacion en tiempo constante para evitar ataques de timing
    private boolean esIgual(String a, String b) {
        return MessageDigest.isEqual(
                a.getBytes(StandardCharsets.UTF_8),
                b.getBytes(StandardCharsets.UTF_8));
    }
}
