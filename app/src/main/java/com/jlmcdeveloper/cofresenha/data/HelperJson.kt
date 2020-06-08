package com.jlmcdeveloper.cofresenha.data

import com.beust.klaxon.Klaxon
import com.jlmcdeveloper.cofresenha.data.model.Book

class HelperJson {
    fun jsonForRepository(json: String) = Klaxon().parseArray<Book>(json)
    fun repositoryForJson(repository: List<Book>) = Klaxon().toJsonString(repository)
}