package com.jlmcdeveloper.cofresenha.ui.listbook

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.cofresenha.data.SafeRepository
import com.jlmcdeveloper.cofresenha.data.model.Book

class ListBookViewModel(private val repository: SafeRepository) : ViewModel() {
    var books = MutableLiveData<MutableList<Book>>()


    fun load() {
        books.postValue(repository.getListBook())
    }
}