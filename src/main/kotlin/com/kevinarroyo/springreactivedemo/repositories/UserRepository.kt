package com.kevinarroyo.springreactivedemo.repositories

import com.kevinarroyo.springreactivedemo.entities.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository : ReactiveMongoRepository<User, String>