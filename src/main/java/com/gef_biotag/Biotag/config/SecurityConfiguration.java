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
                .requestMatchers(HttpMethod.PUT, "/funcionario/**").permitAll() 
                .requestMatchers(HttpMethod.DELETE, "/funcionario/**").permitAll() 
                .requestMatchers(HttpMethod.POST, "/paciente/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/paciente/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/paciente/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/paciente/**").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()


                // 🔐 Somente ADMINISTRADOR pode acessar essas rotas:
                .requestMatchers(HttpMethod.POST, "/abrigos/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.GET, "/abrigos/**").hasRole("ADMINISTRADOR")
                // .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMINISTRADOR")
                // .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMINISTRADOR")
                // .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMINISTRADOR")
                
                
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
