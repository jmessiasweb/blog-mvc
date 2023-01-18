package com.pagina.blog.service

import com.pagina.blog.model.User
import com.pagina.blog.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    fun findByEmail(email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }

    fun save(user: User): User {
        return userRepository.save(user)
    }

}