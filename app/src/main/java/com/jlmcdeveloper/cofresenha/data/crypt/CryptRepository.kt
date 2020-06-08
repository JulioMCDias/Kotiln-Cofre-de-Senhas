package com.jlmcdeveloper.cofresenha.data.crypt

class CryptRepository(private var password: String) {

    fun encrypt(repository: String): String? = AESCrypt.encrypt(repository, password)
    fun decrypt(crypt: String?): String? = AESCrypt.decrypt(crypt, password)
}