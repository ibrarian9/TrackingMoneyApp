package com.app.trackingmoney.ui.screen.addAmount

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.trackingmoney.data.models.Category
import com.app.trackingmoney.data.models.Transaction
import com.app.trackingmoney.data.models.Type
import com.app.trackingmoney.ui.common.DropdownMenu
import com.app.trackingmoney.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAmountScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    navController: NavController,
    viewModel: AddAmountViewModel = koinViewModel()
) {

    // Local mutable state for UI fields
    var amount by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    // State for Category dropdown
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    // Observe categories from the ViewModel
    val categories by viewModel.categories.collectAsState()

    // State for Type dropdown
    var selectedType by remember { mutableStateOf<Type?>(null) }
    // Observe types from the ViewModel
    val types by viewModel.types.collectAsState()

    val context = LocalContext.current

    if (showDatePicker) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                // Format the date string as needed. Here it's YYYY-M-D.
                selectedDate = "$year-${month + 1}-$dayOfMonth"
                showDatePicker = false
            },
            LocalDate.now().year,
            LocalDate.now().monthValue - 1, // month is zero-indexed
            LocalDate.now().dayOfMonth
        ).show()
    }

    Column(
        modifier = modifier.fillMaxSize()
            .background(color = Color.White)
            .padding(20.dp)
            .padding(paddingValues = innerPadding)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 20.dp),
            text = "Transaction",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
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
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
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
        DropdownMenu(
            listData = categories,
            selectedOption = selectedCategory,
            onOptionSelected = { selectedCategory = it },
            placeholder = "Select Category",
            optionToString = { it.nameCategory }
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
            onValueChange = { selectedDate = it },
            placeholder = { Text(text = "Tap to select a date", color = Color.Gray) },
            enabled = false,
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledTextColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Type")
        Spacer(modifier = Modifier.size(5.dp))
        // Type Dropdown
        DropdownMenu(
            listData = types,
            selectedOption = selectedType,
            onOptionSelected = { selectedType = it },
            placeholder = "Select Type",
            optionToString = { it.nameType }
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
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            )
        )
        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 20.dp),
            onClick = {
                // Parse and validate amount
                val parsedAmount = amount.toDoubleOrNull()
                when {
                    parsedAmount == null || parsedAmount <= 0.0 -> {
                        messageToast(context, "Please add a valid amount greater than zero")
                        return@Button
                    }
                    selectedCategory == null -> {
                        messageToast(context, "Please select a category")
                        return@Button
                    }
                    selectedType == null -> {
                        messageToast(context, "Please select a type")
                        return@Button
                    }
                }

                // Parse selected date; if error, fall back to current date
                val formatter = DateTimeFormatter.ofPattern("yyyy-M-d")
                val dateTime = try {
                    LocalDate.parse(selectedDate, formatter).atStartOfDay()
                } catch (ex: Exception) {
                    LocalDateTime.now()
                }

                // Build a Transaction object with the sanitized/validated values
                val transaction = Transaction(
                    id = 0, // auto-generated primary key
                    amount = parsedAmount,
                    date = dateTime,
                    categoryId = selectedCategory!!.id,  // now safe: we know it’s not null
                    note = notes.takeIf { it.isNotBlank() },
                    typeId = selectedType!!.id  // safe because we’ve checked non-null above
                )

                // Call the ViewModel function to insert the transaction.
                viewModel.addTransaction(transaction)

                // Show a success message.
                messageToast(context, "Transaction successfully added")

                // Navigate to HomePage.
                navController.navigate(Screen.HomePage.route) {
                    popUpTo(Screen.AddTransactionPage.route) { inclusive = true }
                }
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Add Transaction",
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

private fun messageToast(context: Context , s: String) {
    Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
}