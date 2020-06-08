package com.jlmcdeveloper.cofresenha.data

import android.net.Uri
import com.jlmcdeveloper.cofresenha.data.crypt.CryptRepository
import com.jlmcdeveloper.cofresenha.data.model.Book
import com.jlmcdeveloper.cofresenha.data.model.Password


class SafeRepository(private val helperFile: HelperFile, private val helperJson: HelperJson) {

    private lateinit var crypt: CryptRepository
    private lateinit var uri: Uri
    private lateinit var repository: List<Book>
    lateinit var fileName: String
        private set
    private lateinit var fileEncryptString: String
    private lateinit var bookName: String



    // ------- open file ------------
    fun setFile(uri: Uri, fileName: String){
        this.uri = uri
        this.fileName = fileName
    }

    fun openFile(success:() -> Unit) {
        fileEncryptString = helperFile.readRepositoryFromUri(uri)
        success()
    }
    //--


    // ------------open crypt ---------------
    fun createPasswordRepository(password: String){
        crypt = CryptRepository(password)
        repository = listOf(
            Book("site"))
        updateFile()
    }


    fun setPasswordRepository(password: String){
        crypt = CryptRepository(password)
        decryptFile()
    }
    //----


    // ------------update file ---------------
    private fun updateFile(){
        crypt.encrypt(helperJson.repositoryForJson(repository))?.let {
            helperFile.updateRepository(uri, it)
        }
    }

    private fun decryptFile(){
        repository = crypt.decrypt(fileEncryptString)?.let { helperJson.jsonForRepository(it) }!!
    }
    //----



    fun getListBook(): MutableList<Book>{
        return MutableList(repository.size) { index->
            Book(repository[index].name, null)
        }
    }

    fun getListPassword(name: String): List<Password>?{
        bookName = name
        return repository.find { it.name == name }?.passwords

    }

    fun addPassword(password: Password){
        repository.find { it.name == bookName }?.passwords?.add(password) ?:
            mutableListOf(password)
        updateFile()
    }

}