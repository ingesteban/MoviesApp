package dev.esteban.test.presentation.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
class TestApp: Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@TestApp)
            modules(
                viewModelModule,
                repositoriesModule,
                useCasesModule,
                mappersModule,
                utilsModule
            )
        }
    }

    init {
        instance = this
    }

    companion object {
        private var instance: TestApp? = null
        fun applicationContext(): TestApp {
            return instance!!
        }
    }
}