package edu.muniz.askalien.admin.config

import net.minidev.json.JSONArray
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authorization.AuthorizationContext
import reactor.core.publisher.Mono


@EnableWebFluxSecurity
@Profile("prod")
class OAuth2SecurityConfig {

    @Bean
    fun security(http: ServerHttpSecurity): SecurityWebFilterChain? {
        http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/wakeup").permitAll()
                .pathMatchers("/admin2/**").access { auth: Mono<Authentication?>, context: AuthorizationContext? ->
                    auth
                            .flatMap {hasAuthority(auth, listOf("admin"))}
                            .map { granted: Boolean? -> AuthorizationDecision(granted!!) }
                }
                .pathMatchers("/admin/**").access { auth: Mono<Authentication?>, context: AuthorizationContext? ->
                    auth
                    .flatMap {hasAuthority(auth, listOf("admin","guest"))}
                    .map { granted: Boolean? -> AuthorizationDecision(granted!!) }
                }
                .and()
                .oauth2ResourceServer()
                .jwt()
        return http.build()
    }


    private fun hasAuthority(authentication: Mono<Authentication?>, authorities: List<String>): Mono<Boolean>? {
        return authentication
                .cast(JwtAuthenticationToken::class.java)
                .map { a -> a.token }
                .map { jwt -> jwt.getClaim<Any>("cognito:groups") }
                .cast(JSONArray::class.java)
                .map { arr -> arr[0] }
                .map { obj -> obj.toString() }
                .map { str -> authorities.contains(str) }
    }


}