package com.app.trackingmoney.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.trackingmoney.data.MainRepository
import com.app.trackingmoney.data.models.BalanceDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val repository: MainRepository
): ViewModel() {

    val balanceDetails: StateFlow<BalanceDetails> = repository.getBalanceDetails()
        .stateIn(viewModelScope, SharingStarted.Eagerly, BalanceDetails(0.0,0.0,0.0))

    // Transform the Flow<List<Transaction>> to a StateFlow<Int> for the transaction count.
    val transactionCount: StateFlow<Int> = repository.getAllTransactionsFlow()
        .map { it.size } // Get the size of the list
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = 0
        )

}