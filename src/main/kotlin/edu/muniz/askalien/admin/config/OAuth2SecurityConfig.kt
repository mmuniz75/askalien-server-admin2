package edu.muniz.askalien.admin.config

import net.minidev.json.JSONArray
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono


@EnableWebFluxSecurity
class OAuth2SecurityConfig {
    @Bean
    fun security(http: ServerHttpSecurity): SecurityWebFilterChain? {
        http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/**").permitAll()
                /*.pathMatchers("/api/ **").access((auth, context) -> auth
                                                   .then(hasAuthority(auth,"WHATSAPP"))
                                                   .map(AuthorizationDecision::new))*/
                //.pathMatchers("/api/**").authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
        return http.build()
    }


    private fun hasAuthority(authentication: Mono<Authentication>, authority: String): Mono<Boolean>? {
        return authentication
                .cast(JwtAuthenticationToken::class.java)
                .map { a: JwtAuthenticationToken -> a.token }
                .map { jwt: Jwt -> jwt.getClaim<Any>("authorities") }
                .cast(JSONArray::class.java)
                .map { arr: JSONArray -> arr[0] }
                .map { obj: Any -> obj.toString() }
                .map { str: String -> str == authority }
    }


}