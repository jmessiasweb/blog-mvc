package com.pagina.blog.service

import com.pagina.blog.model.Category
import com.pagina.blog.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun findAll(): List<Category> {
        return categoryRepository.findAll()
    }

}