package com.app.trackingmoney.ui.screen.addAmount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.trackingmoney.data.MainRepository
import com.app.trackingmoney.data.models.Category
import com.app.trackingmoney.data.models.Transaction
import com.app.trackingmoney.data.models.Type
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AddAmountViewModel(
    val repository: MainRepository
): ViewModel() {

    fun addTransaction(data: Transaction) {
        viewModelScope.launch {
            repository.insertAllTransaction(data)
        }
    }

    // Expose the category list as a Flow (or LiveData if you prefer)
    val categories: StateFlow<List<Category>> = flow {
        emit(repository.getAllCategory())
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // Expose the type list as a Flow
    val types: StateFlow<List<Type>> = flow {
        emit(repository.getAllTypes())
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}