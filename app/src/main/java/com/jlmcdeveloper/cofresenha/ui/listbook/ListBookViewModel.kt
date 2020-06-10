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

    fun logout(){
        repository.logout()
    }

    fun addBook(book: String){
        repository.addBook(book)
        load()
    }

    fun update(book: String, pos: Int){
        repository.updateBook(book, pos)
        load()
    }

    fun removeItem(book: Book){
        repository.removeItemBook(book)
    }

    fun restoreItem(book: Book, position: Int){
        repository.restoreItemBook(book, position)
    }
}