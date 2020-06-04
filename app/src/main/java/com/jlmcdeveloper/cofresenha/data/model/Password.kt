package com.jlmcdeveloper.cofresenha.data.model

data class Password(
    val title: String,
    val name: String? = null,
    val email: String,
    val password: String,
    val description: String? = null
)