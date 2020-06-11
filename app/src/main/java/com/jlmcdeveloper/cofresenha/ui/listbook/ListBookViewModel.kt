package com.jlmcdeveloper.cofresenha.ui.listbook

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.cofresenha.data.SafeRepository
import com.jlmcdeveloper.cofresenha.data.model.Book
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListBookViewModel(private val repository: SafeRepository) : ViewModel() {
    var books = MutableLiveData<MutableList<Book>>()

    val loadingVisibility = MutableLiveData(false)

    fun load() {
        books.postValue(repository.getListBook())
    }

    fun logout(){
        repository.logout()
    }

    fun addBook(book: String){
        GlobalScope.launch {
            loadingVisibility.postValue(true)
            repository.addBook(book)
            load()
            loadingVisibility.postValue(false)
        }
    }

    fun update(book: String, pos: Int){
        GlobalScope.launch {
            loadingVisibility.postValue(true)
            repository.updateBook(book, pos)
            load()
            loadingVisibility.postValue(false)
        }
    }

    fun removeItem(book: Book){
        GlobalScope.launch {
            loadingVisibility.postValue(true)
            repository.removeItemBook(book)
            loadingVisibility.postValue(false)
        }
    }

    fun restoreItem(book: Book, position: Int){
        GlobalScope.launch {
            loadingVisibility.postValue(true)
            repository.restoreItemBook(book, position)
            loadingVisibility.postValue(false)
        }
    }
}