package com.wise.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private ReactiveClientRegistrationRepository clientRegistrationRepository;

    @Value("${spring.security.oauth2.client.provider.azure.jwk-set-uri}")
    private String jwkSetUri;

    @Value("${LOGOUT_URI}")  // Ex: URI de logout do Azure
    private String logoutURI;

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable);

        http.authorizeExchange(conf -> conf
                        .pathMatchers("/login", "/logout").permitAll()
                        .anyExchange().authenticated())
                .oauth2Login(conf -> conf
                        .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("http://localhost:5173/inicial")))
                .oauth2ResourceServer(conf -> conf
                        .jwt(jwt -> jwt.jwtDecoder(jwtDecoder())))
                .logout(logout -> logout
                        .logoutSuccessHandler(azureLogoutSuccessHandler()));  // Usando o Azure Logout
        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

    // Handler que redireciona para o endpoint de logout do Azure AD
    @Bean
    public ServerLogoutSuccessHandler azureLogoutSuccessHandler() {
        return (exchange, authentication) -> {
            // Obtém o ServerWebExchange a partir do WebFilterExchange
            ServerWebExchange webExchange = exchange.getExchange();

            // Redireciona o usuário para o endpoint de logout do Azure AD
            webExchange.getResponse().setStatusCode(HttpStatus.FOUND);
            webExchange.getResponse().getHeaders().setLocation(URI.create(logoutURI));  // URI de logout do Azure
            return webExchange.getResponse().setComplete();
        };
    }
}
