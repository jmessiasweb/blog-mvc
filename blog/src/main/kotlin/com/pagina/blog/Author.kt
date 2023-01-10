package com.pagina.blog

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class Author(

    @Id
    @GeneratedValue
    val id: Long = 0,
    var about: String = "",

    @OneToOne
    var user: User = User()
)
