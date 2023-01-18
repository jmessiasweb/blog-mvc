package com.pagina.blog.controller

import com.pagina.blog.model.User
import com.pagina.blog.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/signup")
class SignUpController(private val service: UserService) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun form(model: Model): String {
        logger.info("form()...")
        model.addAttribute("user", User())
        return "signup"
    }

    @PostMapping
    fun save(user: User, confirmPassword: String, model: Model): String {
        logger.info("save(${user})...")

        if (user.password != confirmPassword) {
            val messageError = "Senha não confere!"
            logger.error(messageError)
            model.addAttribute("messageError", messageError)
            return "signup"
        }

        service.save(user).also { logger.info(user.toString()) }
        return "redirect:/login"
    }

}