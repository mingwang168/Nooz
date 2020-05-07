package com.mingwang.nooz

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(AppModule)
        }
    }
}

val AppModule = module {
    viewModel { ArticlesViewModel() }
}