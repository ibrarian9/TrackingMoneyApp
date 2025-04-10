package com.app.trackingmoney.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.trackingmoney.helper.ObjectHelper.BALANCE
import com.app.trackingmoney.helper.ObjectHelper.EXPENSE
import com.app.trackingmoney.helper.ObjectHelper.INCOME
import com.app.trackingmoney.helper.ObjectHelper.SUMMARY
import com.app.trackingmoney.helper.ObjectHelper.TRANSACTION
import com.app.trackingmoney.ui.screen.home.HomeViewModel
import com.app.trackingmoney.ui.theme.MediumBlue
import com.app.trackingmoney.ui.theme.Warm
import org.koin.androidx.compose.koinViewModel

@Composable
fun BalanceCommon(
    onClickListener: () -> Unit,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val balance = homeViewModel.balanceDetails.collectAsState().value.balance
    val income = homeViewModel.balanceDetails.collectAsState().value.totalIncome
    val expense = homeViewModel.balanceDetails.collectAsState().value.totalExpense
    val transaction = homeViewModel.transactionCount.collectAsState().value

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            text = SUMMARY,
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black
                ).background(color = Color.White),
            onClick = onClickListener
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = Color.Black,
                contentDescription = "Add Transaction"
            )
        }
    }
    Row(
        modifier = Modifier.padding(10.dp)
    ) {
        BoxCommon(
            modifier = Modifier.weight(1f),
            name = BALANCE,
            value = balance.toInt(),
            image = Icons.Default.AccountBalance,
            colorIcon = MediumBlue
        )
        Spacer(modifier = Modifier.padding(5.dp))
        BoxCommon(
            modifier = Modifier.weight(1f),
            name = TRANSACTION,
            value = transaction,
            image = Icons.Filled.SyncAlt,
            colorIcon = Warm
        )
    }
    Row(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        BoxCommon(
            modifier = Modifier.weight(1f),
            name = EXPENSE,
            value = expense.toInt(),
            image = Icons.Default.ArrowCircleDown,
            colorIcon = Color.Red
        )
        Spacer(modifier = Modifier.padding(5.dp))
        BoxCommon(
            modifier = Modifier.weight(1f),
            name = INCOME,
            value = income.toInt(),
            image = Icons.Default.ArrowCircleUp,
            colorIcon = Color.Green
        )
    }
    Spacer(modifier = Modifier.padding(10.dp))
}