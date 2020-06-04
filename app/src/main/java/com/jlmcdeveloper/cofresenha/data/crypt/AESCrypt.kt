package com.jlmcdeveloper.cofresenha.data.crypt

import android.util.Base64
import java.nio.charset.StandardCharsets.UTF_8
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object AESCrypt {
    private const val ALGORITHM = "AES/CBC/PKCS7Padding"


    @Throws(Exception::class)
    fun encrypt(value: String, password: String): String {
        val key: Key = generateKey(password)
        val cipher: Cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptedByteValue: ByteArray =
            cipher.doFinal(value.toByteArray(UTF_8))
        return Base64.encodeToString(encryptedByteValue, Base64.DEFAULT)
    }

    @Throws(Exception::class)
    fun decrypt(value: String?, password: String): String {
        val key: Key = generateKey(password)
        val cipher: Cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, key)
        val decryptedValue64: ByteArray = Base64.decode(value, Base64.DEFAULT)
        val decryptedByteValue: ByteArray = cipher.doFinal(decryptedValue64)
        return String(decryptedByteValue, UTF_8)
    }

    @Throws(Exception::class)
    private fun generateKey(password: String): Key {
        return SecretKeySpec(password.toByteArray(), ALGORITHM)
    }
}