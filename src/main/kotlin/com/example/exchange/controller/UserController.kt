package com.example.exchange.controller

import com.example.exchange.model.User
import com.example.exchange.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(@Autowired private val userService: UserService) {

    @PostMapping("/signup")
    fun signup(@RequestBody user: User): ResponseEntity<User> {
        val newUser = userService.signup(user)
        return ResponseEntity.ok(newUser)
    }

    @PostMapping("/login")
    fun login(@RequestBody user: User): ResponseEntity<User?> {
        val loggedInUser = userService.login(user.username, user.password)
        return if (loggedInUser != null) {
            ResponseEntity.ok(loggedInUser)
        } else {
            ResponseEntity.status(401).body(null)
        }
    }

    @PutMapping("/update")
    fun updateUser(@RequestBody user: User): ResponseEntity<User> {
        val updatedUser = userService.updateUser(user)
        return ResponseEntity.ok(updatedUser)
    }

    @DeleteMapping("/delete/{username}")
    fun deleteUser(@PathVariable username: String): ResponseEntity<Void> {
        userService.deleteUser(username)
        return ResponseEntity.noContent().build()
    }
}