package edu.muniz.askalien.admin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


@EnableWebFluxSecurity
@Profile("test")
class OAuth2SecurityConfigTest {

    @Bean
    fun security(http: ServerHttpSecurity): SecurityWebFilterChain? {
        http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/**").permitAll()

        return http.build()
    }





}