package com.pagina.blog

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/signup")
class SignUpController {

    @RequestMapping
    fun form(): String{
        println("form()...")
        return "signup"
    }

    @PostMapping
    fun save(user: User, confirmaPassword: String): String{
        println("save()...")
        println("name: $user")
        println("confirmaPassword: $confirmaPassword")
        return "redirect:/"
    }
}