package com.gef_biotag.Biotag.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final SecurityFilter securityFilter;
    @Autowired
    public SecurityConfiguration(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth

                 // 🔓 Libera Swagger
               .requestMatchers(
    "/swagger-ui.html",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/v3/api-docs",
                "/swagger-resources/**",
                "/webjars/**"
            ).permitAll()

                .requestMatchers(HttpMethod.POST, "/funcionario/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/funcionario/**").hasRole("ADMINASTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/funcionario/**").hasRole("ADMINASTRADOR")
                .requestMatchers(HttpMethod.POST, "/paciente/**").hasAnyRole("ADMINASTRADOR", "FUNCIONARIO")
                .requestMatchers(HttpMethod.GET, "/paciente/**").hasAnyRole("ADMINASTRADOR", "FUNCIONARIO")
                .requestMatchers(HttpMethod.PUT, "/paciente/**").hasRole("ADMINASTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/paciente/**").hasRole("ADMINASTRADOR")
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()


                // 🔐 Somente ADMINISTRADOR pode acessar essas rotas:
                .requestMatchers(HttpMethod.POST, "/abrigos/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/abrigos/**").permitAll()
                 .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMINASTRADOR")
                 .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMINASTRADOR")
                 .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMINASTRADOR")
                
                
                .anyRequest().authenticated()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
