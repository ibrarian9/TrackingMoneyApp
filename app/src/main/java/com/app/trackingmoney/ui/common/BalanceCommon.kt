package com.app.trackingmoney.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.trackingmoney.ui.theme.MediumBlue
import com.app.trackingmoney.ui.theme.Warm

@Composable
fun BalanceCommon() {
    Text(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp),
        text = "Summary",
        color = Color.White,
        style = MaterialTheme.typography.headlineLarge
    )
    Spacer(modifier = Modifier.padding(10.dp))
    Row(
        modifier = Modifier.padding(10.dp)
    ) {
        BoxCommon(
            modifier = Modifier.weight(1f),
            name = "Balance",
            value = 1000,
            image = Icons.Default.AccountBalance,
            colorIcon = MediumBlue
        )
        Spacer(modifier = Modifier.padding(5.dp))
        BoxCommon(
            modifier = Modifier.weight(1f),
            name = "Transaction",
            value = 2000,
            image = Icons.Filled.SyncAlt,
            colorIcon = Warm
        )
    }
    Row(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        BoxCommon(
            modifier = Modifier.weight(1f),
            name = "Expenses",
            value = 1000,
            image = Icons.Default.ArrowCircleDown,
            colorIcon = Color.Red
        )
        Spacer(modifier = Modifier.padding(5.dp))
        BoxCommon(
            modifier = Modifier.weight(1f),
            name = "Income",
            value = 2000,
            image = Icons.Default.ArrowCircleUp,
            colorIcon = Color.Green
        )
    }
    Spacer(modifier = Modifier.padding(10.dp))
}