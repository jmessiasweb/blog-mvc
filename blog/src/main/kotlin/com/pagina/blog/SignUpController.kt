package com.pagina.blog

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/signup")
class SignUpController(private val repository: UserRepository) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @RequestMapping
    fun form(model: Model): String{
        logger.info("form()...")
        model.addAttribute("user", User())

        return "signup"
    }

    @PostMapping
    fun save(user: User, confirmPassword: String, model: Model): String{
        logger.info("save()...")

        if (user.password != confirmPassword){
            val messageError = "Senha nao confere!"
            logger.error(messageError)
            model.addAttribute("messageError", messageError )
            return "signup"
        }

        repository.save(user).also { logger.info("$user") }

        return "redirect:/login"
    }
}