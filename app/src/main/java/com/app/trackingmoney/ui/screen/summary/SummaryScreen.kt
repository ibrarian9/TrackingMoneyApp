package com.app.trackingmoney.ui.screen.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.trackingmoney.ui.common.BalanceCommon
import com.app.trackingmoney.ui.common.ExpenseContent
import com.app.trackingmoney.ui.common.IncomeContent
import kotlinx.coroutines.launch

@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    // You can pass the content composables as parameters if you want.
    incomeContent: @Composable () -> Unit = { IncomeContent() },
    expenseContent: @Composable () -> Unit = { ExpenseContent() }
) {
    // Define tab titles.
    val tabTitles = listOf("Income", "Expense")
    // Create pager state.
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { tabTitles.size }
    )
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues = innerPadding)
            .background(color = Color.Black)

    ) {
        BalanceCommon()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = Color.White
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(text = title) },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }
            // Horizontal pager for each page content.
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                when (page) {
                    0 -> incomeContent()   // Income page content.
                    1 -> expenseContent()  // Expense page content.
                    // You can add more pages if needed.
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SummaryScreenPreview(){
    SummaryScreen(innerPadding = PaddingValues(20.dp))
}