package com.pagina.blog

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface AuthorRepository: JpaRepository<Author, Long> {
}