package com.turnoflex.turnoflex_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Rutas publicas: login y el frontend (HTML, CSS, JS)
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/", "/index.html", "/*.css", "/*.js").permitAll()


                        .requestMatchers("/api/reportes/**").hasRole("COORDINADOR")

                        // Solo COORDINADOR puede aprobar/rechazar novedades
                        .requestMatchers(HttpMethod.PUT, "/api/novedades/*/estado").hasRole("COORDINADOR")

                        // Todo lo demas bajo /api/** requiere estar autenticado (con token valido)
                        .requestMatchers("/api/**").authenticated()

                        // Cualquier otra ruta, publica
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}