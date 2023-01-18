package com.pagina.blog.configuration

import com.pagina.blog.model.Article
import com.pagina.blog.model.Author
import com.pagina.blog.model.Category
import com.pagina.blog.model.User
import com.pagina.blog.repository.ArticleRepository
import com.pagina.blog.repository.AuthorRepository
import com.pagina.blog.repository.CategoryRepository
import com.pagina.blog.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class DataLoaderConfiguration(
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository,
    private val articleRepository: ArticleRepository,
    private val authorRepository: AuthorRepository
) : CommandLineRunner {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
        loadUser()
        loadCategories()
        loadArticles()
    }

    private fun loadCategories() {
        if (categoryRepository.count() == 0L) {
            listOf(
                    Category(name = "Technology"),
                    Category(name = "World"),
                    Category(name = "U.S."),
                    Category(name = "Design"),
                    Category(name = "Culture"),
                    Category(name = "Business"),
                    Category(name = "Politics"),
                    Category(name = "Opinion"),
                    Category(name = "Science"),
                    Category(name = "Health"),
                    Category(name = "Style"),
                    Category(name = "Travel")
            ).also { categoryRepository.saveAll(it) }
        }
    }

    private fun loadUser() {
        if (userRepository.count() == 0L) {

            listOf(
                    User(
                            name = "Administrator",
                            email = "admin@blog.com",
                            password = "admin"
                    ),
                    User(
                            name = "Admin",
                            email = "teste@blog.com",
                            password = "admin"
                    )
            ).also { userRepository.saveAll(it) }
        }
    }

    private fun loadArticles() {
        if (articleRepository.count() == 0L) {
            val categoryTechnology = categoryRepository.findAll().get(0)
            val categoryWorld = categoryRepository.findAll().get(1)
            val authors = authorRepository.saveAll(
                    listOf(
                            Author(
                                    user = userRepository.findAll().get(0),
                                    about = "When an unknown printer took a galley of type and scrambled it to make a type specimen book."
                            ),
                            Author(
                                    user = userRepository.findAll().get(1),
                                    about = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
                            )
                    )
            )
            listOf(
                    Article(
                            title = "is simply dummy text of the printing and typesettin",
                            subTitle = "industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                            content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                            date = LocalDateTime.now(),
                            author = authors.get(0),
                            category = categoryTechnology
                    ),
                    Article(
                            title = "There are many variations of passages of Lorem Ipsum available",
                            subTitle = "or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything",
                            content = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.",
                            date = LocalDateTime.now(),
                            author = authors.get(1),
                            category = categoryWorld
                    )
            ).also { articleRepository.saveAll(it) }
        }
    }
}