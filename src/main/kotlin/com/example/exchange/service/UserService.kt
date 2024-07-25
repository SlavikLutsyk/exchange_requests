package com.example.exchange.service

import com.example.exchange.model.User
import com.example.exchange.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

    fun signup(user: User): User {
        val existingUser = userRepository.findByUsername(user.username)
        if (existingUser != null) {
            throw IllegalArgumentException("Username ${user.username} already exists")
        }
        return userRepository.save(user)
    }


    fun login(username: String, password: String): User? {
        val user = userRepository.findByUsername(username)
        if (user == null) throw IllegalArgumentException("Username $username not found")
        return if (user != null && user.password == password) user else null
    }

    fun updateUser(user: User): User {
        return userRepository.save(user)
    }

    fun deleteUser(username: String) {
        userRepository.deleteById(username)
    }
}