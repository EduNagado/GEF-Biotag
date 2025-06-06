package com.gef_biotag.Biotag.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gef_biotag.Biotag.repository.FuncionarioRepository;
import com.gef_biotag.Biotag.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final FuncionarioRepository funcionarioRepository;
    @Autowired
    public SecurityFilter(TokenService tokenService, FuncionarioRepository funcionarioRepository) {
        this.tokenService = tokenService;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        if (uri.startsWith("/swagger") || uri.startsWith("/v3/api-docs") || uri.startsWith("/webjars")) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);
        if (token != null) {
            var email = tokenService.validarToken(token);

            var optionalFuncionario = this.funcionarioRepository.findByEmail(email);

            optionalFuncionario.ifPresent(funcionario -> {
                var authentication = new UsernamePasswordAuthenticationToken(
                        funcionario, null, funcionario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        }
        filterChain.doFilter(request, response);
    }

    
    private String recoverToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization"); // "Bearer jdhkjsdhfjghsdfghjsdhfgshdfgh"
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.replace("Bearer ", ""); //"jdhkjsdhfjghsdfghjsdhfgshdfgh"
    }
    
}
