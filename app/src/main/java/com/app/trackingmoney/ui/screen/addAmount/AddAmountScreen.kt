package com.app.trackingmoney.ui.screen.addAmount

import android.app.DatePickerDialog
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun AddAmountScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues
) {
    val onDateSelected: (String) -> Unit
    var selectedDate = ""
    var amount by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val context = LocalContext.current

    var notes by remember { mutableStateOf("") }
    // For category dropdown
    var expanded by remember { mutableStateOf(false) }

    // Get current date from the Calendar
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

    if (showDatePicker) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                // Format the date string as needed. Here it's YYYY-M-D.
                onDateSelected("$year-${month + 1}-$dayOfMonth")
                showDatePicker = false
            },
            currentYear,
            currentMonth,
            currentDay
        ).show()
    }

    Column(
        modifier = modifier.fillMaxSize()
            .padding(20.dp)
            .padding(paddingValues = innerPadding)
    ) {
        // Amount
        Text(text = "Amount")
        Spacer(modifier = Modifier.size(5.dp))
        TextField(
            modifier = Modifier.fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            value = amount,
            onValueChange = { amount = it },
            placeholder = {
                Text(
                    text = "Input Amount",
                    color = Color.Gray
            )}
        )
        Spacer(modifier = Modifier.size(10.dp))

        // Category Dropdown
        Text(text = "Category")
        Spacer(modifier = Modifier.size(5.dp))
        TextField(
            modifier = Modifier.fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Input Category",
                    color = Color.Gray
                )
            }
        )
        Spacer(modifier = Modifier.size(10.dp))
        // Date (non-editable, but clickable to show the DatePickerDialog)
        Text(text = "Date", color = Color.Black)
        Spacer(modifier = Modifier.size(5.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                .clickable { showDatePicker = true },
            value = selectedDate,
            onValueChange = {},
            placeholder = { Text(text = "Tap to select a date", color = Color.Gray) },
            enabled = false,
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.size(10.dp))

        // Notes Input Field
        Text(text = "Notes", color = Color.Black)
        Spacer(modifier = Modifier.size(5.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)),
            value = notes,
            onValueChange = { notes = it },
            placeholder = { Text(text = "Input Notes", color = Color.Gray) },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddAmountScreenPreview(){
    AddAmountScreen(
        innerPadding = PaddingValues(20.dp)
    )
}
