package edu.muniz.askalien.admin.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import java.util.List


@Configuration
@EnableWebFlux
class CorsGlobalConfiguration {

    @Value("\${application.website}")
    private lateinit var servers: String

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = List.of(servers)
        configuration.addAllowedHeader("Authorization")
        configuration.addAllowedHeader("Content-Type")
        configuration.addAllowedMethod("*")
        configuration.maxAge = 1L
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}