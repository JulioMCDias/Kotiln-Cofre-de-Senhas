package com.jlmcdeveloper.cofresenha

import com.beust.klaxon.Klaxon
import com.jlmcdeveloper.cofresenha.data.crypt.AESCrypt
import com.jlmcdeveloper.cofresenha.data.model.Book
import com.jlmcdeveloper.cofresenha.data.model.Password
import org.junit.Assert.assertEquals
import org.junit.Test
import java.nio.charset.StandardCharsets
import java.security.spec.KeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun klaxonTest(){

        val repository = listOf(
            Book(
                listOf(
                    Password("title1", email = "name1", password = "password1"),
                    Password("title2", email = "name2", password = "password2")
                )
            ),
            Book(
                listOf(
                    Password("title1", email = "name1", password = "password1"),
                    Password("title2", email = "name2", password = "password2")
                )
            )
        )

        println(repository)

        val result = Klaxon().toJsonString(repository)

        println(result)

        val result2 = Klaxon().parseArray<Book>(result)

        println(result2)
    }
}
