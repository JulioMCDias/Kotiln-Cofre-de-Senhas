package com.jlmcdeveloper.cofresenha.ui.addpassword

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.data.SafeRepository
import com.jlmcdeveloper.cofresenha.data.model.Password
import com.jlmcdeveloper.cofresenha.utils.validateCampEmpty

class AddPasswordViewModel(private val repository: SafeRepository, private val context: Context) : ViewModel() {
    val errorTitle = MutableLiveData<String?>()
    val errorEmail = MutableLiveData<String?>()
    val errorPassword = MutableLiveData<String?>()

    val title = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    lateinit var finish: () -> Unit

    fun save(){
        if (validateCampEmpty(title, errorTitle, context.getString(R.string.campNull)) and
            validateCampEmpty(email, errorEmail, context.getString(R.string.campNull)) and
            validateCampEmpty(password, errorPassword, context.getString(R.string.campNull))){

            repository.addPassword(
                Password(title.value!!, name.value, email.value!!, password.value!!, description.value)
            )

            finish()
        }
    }

}