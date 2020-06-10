package com.jlmcdeveloper.cofresenha.data.model

data class Book(
    var name: String,
    val passwords: MutableList<Password>? = mutableListOf()
)

