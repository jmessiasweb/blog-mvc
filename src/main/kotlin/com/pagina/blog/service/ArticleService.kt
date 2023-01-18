package com.pagina.blog.service

import com.pagina.blog.model.Article
import com.pagina.blog.model.Author
import com.pagina.blog.model.User
import com.pagina.blog.repository.ArticleRepository
import com.pagina.blog.repository.AuthorRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ArticleService(
    private val articleRepository: ArticleRepository,
    private val authorRepository: AuthorRepository
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    fun save(article: Article, currentUser: User): Article {
        logger.info("save()...")
        val authorOptional: Optional<Author> = authorRepository.findByUserId(currentUser.id)
        val author = if (authorOptional.isPresent) {
            authorOptional.get()
        } else {
            val author = Author(user = currentUser)
            authorRepository.save(author).also { logger.info("Autor criado com sucesso!") }
        }

        article.author = author
        article.date = LocalDateTime.now()
        return articleRepository.save(article)
    }

    fun findAll(page: Int = 0): Page<Article> {
        val pageRequest = PageRequest.of(page, 1, Sort.by(Sort.Direction.DESC, "id"))
        return articleRepository.findAll(pageRequest)
    }

    fun findByAuthorUserId(idUser: Long, sort: Sort): List<Article> {
        return articleRepository.findByAuthorUserId(idUser, sort)
    }

    fun findByCategoryId(idCategory: Long, sort: Sort): List<Article> {
        return articleRepository.findByCategoryId(idCategory, sort)
    }

    fun findById(id: Long): Article {
        return articleRepository.findById(id).get()
    }

    fun deleteById(id: Long) {
        articleRepository.deleteById(id)
    }

}