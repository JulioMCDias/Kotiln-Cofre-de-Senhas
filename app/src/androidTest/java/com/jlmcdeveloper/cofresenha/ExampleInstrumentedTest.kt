package com.jlmcdeveloper.cofresenha

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jlmcdeveloper.cofresenha.data.crypt.AESCrypt

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.jlmcdeveloper.cofresenha", appContext.packageName)
    }

    @Test
    fun cryptTest(){
        val password = "minha senha"

        val encript = AESCrypt.encrypt("hello word ola tudo bem", password)
        Log.d("TAGTest","saida da encrypt: $encript")


        Log.d("TAGTest","saida da converção: "+ AESCrypt.decrypt(encript, password))
    }
}
