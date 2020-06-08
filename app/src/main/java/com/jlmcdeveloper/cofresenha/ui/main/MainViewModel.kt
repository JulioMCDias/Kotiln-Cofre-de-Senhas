package com.jlmcdeveloper.cofresenha.ui.main

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.cofresenha.data.SafeRepository
import com.jlmcdeveloper.cofresenha.utils.OpenFileFramework

class MainViewModel(private val repository: SafeRepository) : ViewModel(){
    lateinit var startActivity: () -> Unit

    fun setFile(uri: Uri, file: OpenFileFramework.File){
        repository.setFile(uri, file.nameFile)

    }

    fun openFile(){
        repository.openFile( startActivity)
    }

}