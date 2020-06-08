package com.jlmcdeveloper.cofresenha.data.crypt

import android.util.Base64
import java.nio.charset.StandardCharsets.UTF_8
import java.security.Key
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object AESCrypt {
    private const val ALGORITHM = "AES"
    private val salt = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00)


    @Throws(Exception::class)
    fun encrypt(value: String, password: String): String? {
        val key: Key = generateKey(password)
        return try {
            val cipher: Cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val encryptedByteValue: ByteArray = cipher.doFinal(value.toByteArray(UTF_8))
            Base64.encodeToString(encryptedByteValue, Base64.DEFAULT)
        } catch (e: Exception) {
            throw RuntimeException(
                "Error occured while encrypting data", e
            )
        }
    }

    @Throws(Exception::class)
    fun decrypt(value: String?, password: String): String? {
        val key: Key = generateKey(password)
        return try {
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.DECRYPT_MODE, key)
            val decryptedValue64: ByteArray = Base64.decode(value, Base64.DEFAULT)
            val decryptedByteValue: ByteArray = cipher.doFinal(decryptedValue64)
            String(decryptedByteValue, UTF_8)
        } catch (e: Exception) {
            throw RuntimeException(
                "Error occured while decrypting data", e
            )
        }
    }

    @Throws(Exception::class)
    private fun generateKey(password: String): Key {
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), salt, 65536, 256)
        val tmp: SecretKey = factory.generateSecret(spec)
        return SecretKeySpec(tmp.encoded, "AES")
    }
}