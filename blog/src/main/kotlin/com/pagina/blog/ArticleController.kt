package com.pagina.blog

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.time.LocalDateTime
import java.util.Optional
import javax.servlet.http.HttpSession


@Controller
@RequestMapping("/article")
class ArticleController(
    private val articleRepository: ArticleRepository,
    private val authorRepository: AuthorRepository
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @RequestMapping
    fun form(model: Model): String {
        logger.info("form()...")
        model.addAttribute("article", Article())

        return "article"

    }

    @PostMapping
    fun save(article: Article, session: HttpSession, redirectAttributes: RedirectAttributes): String {
        logger.info("save($article)")

        val currentUser = session.getAttribute("currentUser") as User
        val authorOptinal: Optional<Author> = authorRepository.findByUserId(currentUser.id)
        val author = if (authorOptinal.isPresent) {
            authorOptinal.get()
        } else {
            val author = Author(user = currentUser)
            authorRepository.save(author).also { logger.info("Autor criado com sucesso!") }
        }
        article.author = author
        article.date = LocalDateTime.now()
        articleRepository.save(article)
        val messageSuccess = "Artigo criado com sucesso!!!"
        logger.info(messageSuccess)
        redirectAttributes.addFlashAttribute("messageSucces", messageSuccess)


        return "redirect:/"
    }
    @GetMapping("/list")
    fun list(): String {
        logger.info("list()...")
        return "article-list"
    }
}