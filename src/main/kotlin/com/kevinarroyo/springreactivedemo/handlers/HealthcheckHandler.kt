package com.kevinarroyo.springreactivedemo.handlers

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class HealthcheckHandler {
    suspend fun healthcheck(request: ServerRequest): ServerResponse {
        return ok().bodyValueAndAwait("Healthcheck Successful!")
    }
}