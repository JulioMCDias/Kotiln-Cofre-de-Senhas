package com.jlmcdeveloper.cofresenha.ui.addpassword

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.data.SafeRepository
import com.jlmcdeveloper.cofresenha.data.model.Password
import com.jlmcdeveloper.cofresenha.utils.validateCampEmpty
import com.jlmcdeveloper.cofresenha.utils.validateEmail

class AddPasswordViewModel(private val repository: SafeRepository, private val context: Context) : ViewModel() {
    private var edit: Password? = null
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
            validateEmail(email, errorEmail, context.getString(R.string.campNull),
                context.getString(R.string.email_valid)) and
            validateCampEmpty(password, errorPassword, context.getString(R.string.campNull))){

            if(edit != null){
                edit!!.title = title.value!!
                edit!!.name = name.value ?: ""
                edit!!.email = email.value!!
                edit!!.password = password.value!!
                edit!!.description = description.value ?: ""
                repository.updateFile()
            }else {
                repository.addPassword(
                    Password(
                        title.value!!,
                        name.value,
                        email.value!!,
                        password.value!!,
                        description.value
                    )
                )
            }
            finish()
        }
    }

    fun editable(title: String?){
        if(title != null)
            setPassword(repository.getPassword(title))
    }

    private fun setPassword(pas: Password){
        edit = pas
        title.postValue(pas.title)
        name.postValue(pas.name)
        email.postValue(pas.email)
        password.postValue(pas.password)
        description.postValue(pas.description)
    }

}