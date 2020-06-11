package com.jlmcdeveloper.cofresenha.ui.repository.open

import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.*
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.data.SafeRepository
import com.jlmcdeveloper.cofresenha.utils.validateCampEmpty
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OpenRepositoryViewModel(private val repository: SafeRepository,
                              private val context: Context) : ViewModel() {

    val loadingVisibility = MutableLiveData(false)

    val editPassword = MutableLiveData<String>()
    val passwordError = MutableLiveData<String?>()
    val uri = MutableLiveData<String?>()


    lateinit var startActivity: () -> Unit

    fun openRepository(){
        loadingVisibility.postValue(true)
        if(validateCampEmpty(editPassword, passwordError, context.getString(R.string.campNull))){

            GlobalScope.launch {
                editPassword.value?.let { repository.setPasswordRepository(it) }
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