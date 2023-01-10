package com.pagina.blog

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/article")
class ArticleController {

    @RequestMapping
    fun form(model: Model): String {
        model.addAttribute("article", Article())

        return "article"

    }
}