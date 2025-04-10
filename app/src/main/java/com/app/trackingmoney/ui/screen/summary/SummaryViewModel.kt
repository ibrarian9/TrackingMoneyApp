package com.app.trackingmoney.ui.screen.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.trackingmoney.data.MainRepository
import com.app.trackingmoney.data.models.Category
import com.app.trackingmoney.data.models.TransactionWithDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class SummaryViewModel(
    val repository: MainRepository
): ViewModel() {

    val incomeTransaction: StateFlow<List<TransactionWithDetails>> = flow {
        emit(repository.getAllIncomeTransaction())
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val expenseTransaction: StateFlow<List<TransactionWithDetails>> = flow {
        emit(repository.getAllExpenseTransaction())
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // Expose the category list as a Flow (or LiveData if you prefer)
    val categories: StateFlow<List<Category>> = flow {
        emit(repository.getAllCategory())
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}