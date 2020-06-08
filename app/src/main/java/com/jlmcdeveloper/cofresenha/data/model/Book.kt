package com.jlmcdeveloper.cofresenha.data.model

data class Book(
    val name: String,
    val passwords: MutableList<Password>? = mutableListOf()
)

