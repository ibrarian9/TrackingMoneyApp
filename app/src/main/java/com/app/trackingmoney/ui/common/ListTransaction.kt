package com.app.trackingmoney.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.trackingmoney.data.models.TransactionWithDetails
import com.app.trackingmoney.helper.toRelativeTimeString

@Composable
fun ListTrasaction(
    modifier: Modifier = Modifier,
    data: TransactionWithDetails
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 10.dp)
            .border(width = 1.dp, color = Color.Black, ShapeDefaults.Medium)
            .padding(5.dp)
    ) {
        Text(text = data.transaction.date.toRelativeTimeString(), fontSize = 8.sp)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(modifier = Modifier.weight(1f), text = data.category.nameCategory)
            Text(modifier = Modifier.weight(1f), text = data.transaction.amount.toString())
        }
        if (!data.transaction.note.isNullOrEmpty()) {
            Text(text = data.transaction.note, fontSize = 8.sp)
        }
    }
}