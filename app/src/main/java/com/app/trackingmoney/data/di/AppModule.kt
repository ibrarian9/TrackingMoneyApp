package com.app.trackingmoney.data.di

import com.app.trackingmoney.data.MainRepository
import com.app.trackingmoney.data.local.AppDatabase
import com.app.trackingmoney.ui.screen.addAmount.AddAmountViewModel
import com.app.trackingmoney.ui.screen.summary.SummaryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Create a Koin module for Room
val roomModule = module {
    // Provide a CoroutineScope for the database callback
    single { CoroutineScope(SupervisorJob() + Dispatchers.IO) }

    // Provide your AppDatabase instance
    single {
        AppDatabase.getDatabase(androidContext(), get())
    }

    // Provide DAOs
    single { get<AppDatabase>().transactionDao() }
    single { get<AppDatabase>().categoryDao() }
    single { get<AppDatabase>().typeDao() }
}

val repoModule = module {
    single { MainRepository(get(), get(), get()) }
}

val viewModelModule = module {
    viewModel { AddAmountViewModel(get()) }
    viewModel { SummaryViewModel(get()) }
}