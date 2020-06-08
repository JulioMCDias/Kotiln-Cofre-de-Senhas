package com.jlmcdeveloper.cofresenha.data.model

data class Password(
    var title: String,
    var name: String? = null,
    var email: String,
    var password: String,
    var description: String? = null
)