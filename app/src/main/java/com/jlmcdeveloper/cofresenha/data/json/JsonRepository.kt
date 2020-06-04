package com.jlmcdeveloper.cofresenha.data.json

import com.beust.klaxon.Klaxon
import com.jlmcdeveloper.cofresenha.data.model.Book

class JsonRepository {

    fun jsonForRepository(json: String) = Klaxon().parseArray<Book>(json)

    fun RepositoryForJson(repository: List<Book>) = Klaxon().toJsonString(repository)
}