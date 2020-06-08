package com.jlmcdeveloper.cofresenha.ui.repository.create

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.data.SafeRepository
import com.jlmcdeveloper.cofresenha.utils.validateCampEmpty

class CreateRepositoryViewModel(private val repository: SafeRepository,
                                private val context: Context) : ViewModel() {

    val editPassword = MutableLiveData<String>()
    val passwordError = MutableLiveData<String?>()
    val uri = MutableLiveData<String?>()


    lateinit var startActivity: () -> Unit

    fun savePassword(){
        if(validateCampEmpty(editPassword, passwordError, context.getString(R.string.campNull))){
            editPassword.value?.let { repository.createPasswordRepository(it) }
            startActivity()
        }
    }


    fun load(){
        uri.postValue(repository.fileName)
    }
}