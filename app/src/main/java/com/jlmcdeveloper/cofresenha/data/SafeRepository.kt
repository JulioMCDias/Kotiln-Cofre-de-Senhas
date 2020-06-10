package com.jlmcdeveloper.cofresenha.data

import android.net.Uri
import com.jlmcdeveloper.cofresenha.data.crypt.CryptRepository
import com.jlmcdeveloper.cofresenha.data.model.Book
import com.jlmcdeveloper.cofresenha.data.model.Password


class SafeRepository(private val helperFile: HelperFile, private val helperJson: HelperJson) {

    private lateinit var crypt: CryptRepository
    private lateinit var uri: Uri
    private lateinit var repository: MutableList<Book>
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
        repository = mutableListOf(
            Book("site"))
        updateFile()
    }


    fun setPasswordRepository(password: String){
        crypt = CryptRepository(password)
        decryptFile()
    }
    //----


    // ------------update file ---------------
    fun updateFile(){
        crypt.encrypt(helperJson.repositoryForJson(repository))?.let {
            helperFile.updateRepository(uri, it)
        }
    }

    private fun decryptFile(){
        repository = crypt.decrypt(fileEncryptString)?.let { helperJson.jsonForRepository(it) }!!.toMutableList()
    }
    //----




    // -------- book --------------
    fun getListBook(): MutableList<Book>{
        return repository
    }

    fun addBook(book: String){
        repository.add(Book(book))
        updateFile()
    }

    fun updateBook(book: String, pos: Int){
        repository[pos].name = book
        updateFile()
    }

    fun removeItemBook(book: Book){
        repository.remove(book)
        updateFile()
    }

    fun restoreItem(book: Book, position: Int){
        repository.add(position, book)
        updateFile()
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

    fun getPassword(title: String): Password{
        return getListPassword(bookName)!!.find { it.title == title }!!
    }
}