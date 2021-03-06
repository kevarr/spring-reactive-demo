package com.kevinarroyo.springreactivedemo.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class User(
    @Id var id: String?,
    var firstName: String,
    var lastName: String
)