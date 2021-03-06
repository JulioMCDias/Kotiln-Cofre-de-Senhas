package com.jlmcdeveloper.cofresenha

import androidx.lifecycle.liveData
import com.beust.klaxon.Klaxon
import com.jlmcdeveloper.cofresenha.data.model.Book
import com.jlmcdeveloper.cofresenha.data.model.Password
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test


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
            Book( "asd",
                mutableListOf(
                    Password("title1", email = "name1", password = "password1"),
                    Password("title2", email = "name2", password = "password2")
                )
            ),
            Book( "name",
                mutableListOf(
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

    @Test
    fun kotlinTest(){
        GlobalScope.launch {
            val test = string().await()
            println(test)
        }
        println("aqui")
        Thread.sleep(2000)
    }

    fun string() = GlobalScope.async{

         "asd"
    }
}
