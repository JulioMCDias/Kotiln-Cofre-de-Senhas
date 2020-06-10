package com.jlmcdeveloper.cofresenha.ui.listpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.cofresenha.data.SafeRepository
import com.jlmcdeveloper.cofresenha.data.model.Password

class ListPasswordViewModel(private val repository: SafeRepository) : ViewModel() {
    var passwords = MutableLiveData<MutableList<Password>>()


    fun load(book: String) {
        passwords.postValue(repository.getListPassword(book)!!.toMutableList())
    }

    fun removeItem(password: Password){
        repository.removeItemPassword(password)
    }

    fun restoreItem(password: Password, position: Int){
        repository.restoreItemPassword(password, position)
    }
}