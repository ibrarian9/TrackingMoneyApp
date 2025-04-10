package com.app.trackingmoney.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.trackingmoney.ui.screen.summary.SummaryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun IncomeContent(
    modifier: Modifier = Modifier,
    summaryViewModel: SummaryViewModel = koinViewModel()
) {
    val transaction by summaryViewModel.incomeTransaction.collectAsState()

    // Render the list of transactions.
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = transaction) { data ->
            Spacer(modifier = Modifier.padding(5.dp))
            ListTrasaction(data = data)
        }
    }
}