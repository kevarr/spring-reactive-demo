package com.kevinarroyo.springreactivedemo.handlers

import com.kevinarroyo.springreactivedemo.entities.User
import com.kevinarroyo.springreactivedemo.repositories.UserRepository
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Component
class UserHandler(val userRepository: UserRepository) {

    suspend fun createUser(request: ServerRequest): ServerResponse {
        return request.bodyToMono<User>()
            .flatMap(userRepository::insert)
            .flatMap { saved: User ->
                ServerResponse.created(URI.create("/users/" + saved.id)).build()
            }.awaitSingle()
    }

    suspend fun getUser(request: ServerRequest): ServerResponse {
        val userId = request.pathVariable("id")

        return userRepository.findById(userId)
            .flatMap { user: User ->
                ServerResponse.ok().json().bodyValue(user)
            }
            .switchIfEmpty(ServerResponse.notFound().build()).awaitSingle()
    }

    suspend fun getAllUsers(request: ServerRequest): ServerResponse {
        val users = userRepository.findAll().asFlow()

        return ServerResponse.ok().json().bodyAndAwait(users)
    }
}