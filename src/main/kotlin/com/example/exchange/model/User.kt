package com.example.exchange.model

import jakarta.persistence.*

@Entity
@Table(name = "UserExchange")
data class User(
    @Id
    @Column(name = "username")
    val username: String,

    @Column(name = "password")
    val password: String
) {
    constructor() : this("", "")
}
