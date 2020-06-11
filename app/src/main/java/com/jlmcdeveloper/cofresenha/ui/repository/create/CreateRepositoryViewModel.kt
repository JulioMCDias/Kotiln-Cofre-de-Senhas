package com.jlmcdeveloper.cofresenha.ui.repository.create

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.data.SafeRepository
import com.jlmcdeveloper.cofresenha.utils.validateCampEmpty
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateRepositoryViewModel(private val repository: SafeRepository,
                                private val context: Context) : ViewModel() {

    val loadingVisibility = MutableLiveData(false)

    val editPassword = MutableLiveData<String>()
    val passwordError = MutableLiveData<String?>()
    val uri = MutableLiveData<String?>()


    lateinit var startActivity: () -> Unit

    fun savePassword(){
        loadingVisibility.postValue(true)
        if(validateCampEmpty(editPassword, passwordError, context.getString(R.string.campNull))){

            GlobalScope.launch {
                editPassword.value?.let { repository.createPasswordRepository(it) }
                loadingVisibility.postValue(false)
                startActivity()
            }

        }else
            loadingVisibility.postValue(false)
    }


    fun load(){
        uri.postValue(repository.fileName)
    }
}