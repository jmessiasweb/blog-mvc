package com.pagina.blog

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ArticleRepository: JpaRepository<Article, Long> {
    fun findByUserId(id: Long): Optional<Author>
}