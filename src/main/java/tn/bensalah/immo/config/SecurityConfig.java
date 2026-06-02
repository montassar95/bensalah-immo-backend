package tn.bensalah.immo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import tn.bensalah.immo.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
           	    .requestMatchers("/h2-console/**").permitAll()
            	    .requestMatchers("/health").permitAll()  // ← ajoute cette ligne
            	    .requestMatchers("/api/auth/**").permitAll()
            	    .requestMatchers(HttpMethod.GET, "/api/biens").permitAll()
            	    .requestMatchers(HttpMethod.GET, "/api/biens/**").permitAll()
            	    .requestMatchers(HttpMethod.POST, "/api/reservations").permitAll()
            	    .requestMatchers(HttpMethod.POST, "/api/contact").permitAll()
            	    .requestMatchers("/api/admin/**").hasRole("ADMIN")
            	    .requestMatchers("/api/biens/admin/**").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.POST, "/api/biens/admin").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.PUT, "/api/biens/admin/**").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.DELETE, "/api/biens/admin/**").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.DELETE, "/api/biens/admin/**").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.PUT, "/api/biens/admin/**").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.GET, "/api/biens/*/reservations/dates").permitAll()
            	    .anyRequest().authenticated()
            	)
            .headers(h -> h.frameOptions(f -> f.disable())) // pour H2 console
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration config = new CorsConfiguration();
//      config.setAllowedOrigins(List.of(
//        "http://localhost:4200",
//        "https://ben-salah-services.netlify.app"
//      ));
//      config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
//      config.setAllowedHeaders(List.of("*"));
//      config.setAllowCredentials(true);
//
//      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//      source.registerCorsConfiguration("/api/**", config);
      config.setAllowedOriginPatterns(List.of("*")); // ✅ tout le monde
      config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
      config.setAllowedHeaders(List.of("*"));
      config.setAllowCredentials(true);

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", config);
      return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}