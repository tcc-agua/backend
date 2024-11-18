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

    // jwk-set-uri da aplicação no azure
    @Value("${spring.security.oauth2.client.provider.azure.jwk-set-uri}")
    private String jwkSetUri;

    // logout-uri da aplicação
    @Value("${LOGOUT_URI}")  // Ex: URI de logout do Azure
    private String logoutURI;

    // Filtro de segurança
    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // Desabilita a proteção contra ataques CSRF, como a aplicação conta com a segurança da autenticação com o token JWT, essa proteção
        // não é necessária.
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                // Desabilita o CORS, como a aplicação controla as origens que podem ou não fazer requisições, o CORS não é necessário
                .cors(ServerHttpSecurity.CorsSpec::disable);

        // Definindo as regras para realizar uma requisição
        http.authorizeExchange(conf -> conf
                        // Permite que os endpoints /login e /logout sejam acessados sem autenticação
                        .pathMatchers("/login", "/logout").permitAll()
                        // Qualquer outro endpoint só pode ser acessado com autenticação
                        .anyExchange().authenticated())
                // Configurando a aplicação com o OAuth2,
                .oauth2Login(conf -> conf
                        // Definindo o endpoint de redirecionamento após o login ser feito com sucesso
                        .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("http://localhost:5173/inicial")))
                // Definindo a proteção da aplicação com tokens JWT
                .oauth2ResourceServer(conf -> conf
                        // Definindo o decodificador JWT para a validação e interpretação dos tokens recebidos
                        .jwt(jwt -> jwt.jwtDecoder(jwtDecoder())))
                // Configuração do logout
                .logout(logout -> logout
                        // Quando o logout for feito com sucesso, esse interpretador será utilizado
                        .logoutSuccessHandler(azureLogoutSuccessHandler()));  // Usando o Azure Logout
        return http.build();
    }

    // Interpretador JWT
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
