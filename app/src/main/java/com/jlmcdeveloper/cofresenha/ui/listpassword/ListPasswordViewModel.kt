package com.jlmcdeveloper.cofresenha.ui.listpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.cofresenha.data.SafeRepository
import com.jlmcdeveloper.cofresenha.data.model.Password
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListPasswordViewModel(private val repository: SafeRepository) : ViewModel() {
    var passwords = MutableLiveData<MutableList<Password>>()

    val loadingVisibility = MutableLiveData(false)

    fun load(book: String) {
        passwords.postValue(repository.getListPassword(book)!!.toMutableList())
    }

    fun removeItem(password: Password){
        GlobalScope.launch {
            loadingVisibility.postValue(true)
            repository.removeItemPassword(password)
            loadingVisibility.postValue(false)
        }
    }

    fun restoreItem(password: Password, position: Int){
        GlobalScope.launch {
            loadingVisibility.postValue(true)
            repository.restoreItemPassword(password, position)
            loadingVisibility.postValue(false)
        }
    }
}