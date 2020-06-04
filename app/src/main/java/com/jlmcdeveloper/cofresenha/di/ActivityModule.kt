package com.jlmcdeveloper.cofresenha.di

import com.jlmcdeveloper.cofresenha.ui.addpassword.AddPasswordViewModel
import com.jlmcdeveloper.cofresenha.ui.listbook.ListBookViewModel
import com.jlmcdeveloper.cofresenha.ui.listpassword.ListPasswordViewModel
import com.jlmcdeveloper.cofresenha.ui.repository.RepositoryViewModel
import com.jlmcdeveloper.cofresenha.ui.splash.SplashViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val splashModule = module { viewModel { SplashViewModel() } }

val repositoryModule = module { viewModel { RepositoryViewModel() } }

val listBookModule = module { viewModel { ListBookViewModel() } }

val listPasswordModule = module { viewModel { ListPasswordViewModel() }}

val addPasswordModule = module { viewModel { AddPasswordViewModel() } }

val activityModules = listOf(
    splashModule, repositoryModule, listBookModule, listPasswordModule, addPasswordModule)