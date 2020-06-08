package com.jlmcdeveloper.cofresenha.di


import com.jlmcdeveloper.cofresenha.data.HelperFile
import com.jlmcdeveloper.cofresenha.data.HelperJson
import com.jlmcdeveloper.cofresenha.data.SafeRepository
import com.jlmcdeveloper.cofresenha.data.crypt.CryptRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val encryptModule = module {
    single { HelperFile(androidContext()) }
    single { HelperJson() }
}

val safeRepositoryModule = module {
    single { SafeRepository(get(), get()) }
}

val appModules = listOf(encryptModule, safeRepositoryModule)