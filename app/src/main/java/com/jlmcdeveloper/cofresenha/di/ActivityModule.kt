package com.jlmcdeveloper.cofresenha.di

import com.jlmcdeveloper.cofresenha.data.SafeRepository
import com.jlmcdeveloper.cofresenha.ui.addpassword.AddPasswordViewModel
import com.jlmcdeveloper.cofresenha.ui.listbook.ListBookViewModel
import com.jlmcdeveloper.cofresenha.ui.listpassword.ListPasswordViewModel
import com.jlmcdeveloper.cofresenha.ui.main.MainViewModel
import com.jlmcdeveloper.cofresenha.ui.repository.create.CreateRepositoryViewModel
import com.jlmcdeveloper.cofresenha.ui.repository.open.OpenRepositoryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val splashModule = module { viewModel { MainViewModel(get() as SafeRepository) } }

val repositoryModule = module {
    viewModel { CreateRepositoryViewModel(get(), androidContext()) }
    viewModel { OpenRepositoryViewModel(get(), androidContext()) }
}

val listBookModule = module { viewModel { ListBookViewModel(get()) } }

val listPasswordModule = module { viewModel { ListPasswordViewModel(get()) }}

val addPasswordModule = module { viewModel { AddPasswordViewModel(get()) } }

val activityModules = listOf(
    splashModule, repositoryModule, listBookModule, listPasswordModule, addPasswordModule)