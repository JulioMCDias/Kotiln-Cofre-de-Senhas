package com.jlmcdeveloper.cofresenha.di

import com.jlmcdeveloper.cofresenha.ui.listbook.ListBookViewModel
import com.jlmcdeveloper.cofresenha.ui.splash.SplashViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val splashModule = module { viewModel { SplashViewModel() } }

val listBookModule = module { viewModel { ListBookViewModel() } }


val activityModules = listOf(splashModule, listBookModule)