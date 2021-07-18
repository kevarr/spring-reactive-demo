package com.kevinarroyo.springreactivedemo.config

import com.kevinarroyo.springreactivedemo.handlers.HealthcheckHandler
import com.kevinarroyo.springreactivedemo.handlers.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class WebConfig {
    @Bean
    fun healthcheckRouter(handler: HealthcheckHandler): RouterFunction<ServerResponse> {
        return coRouter {
            GET("/healthcheck", handler::healthcheck)
        }
    }

    @Bean
    fun userRoutes(handler: UserHandler): RouterFunction<ServerResponse> {
        return coRouter {
            accept(APPLICATION_JSON).nest {
                GET("/users/{id}", handler::getUser)
                GET("/users", handler::getAllUsers)
            }
            POST("/user", handler::createUser)
        }
    }
}