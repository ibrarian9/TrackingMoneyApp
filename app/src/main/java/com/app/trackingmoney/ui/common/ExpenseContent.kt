package com.app.trackingmoney.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.app.trackingmoney.ui.screen.summary.SummaryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExpenseContent(
    modifier: Modifier = Modifier,
    summaryViewModel: SummaryViewModel = koinViewModel()
) {
    val transaction by summaryViewModel.expenseTransaction.collectAsState()

    // Render the list of transactions.
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = transaction) { data ->
            ListTrasaction(data = data)
        }
    }
}
