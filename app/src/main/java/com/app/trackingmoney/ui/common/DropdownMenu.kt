package com.app.trackingmoney.ui.common

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownMenu(
    listData: List<T>,
    selectedOption: T?,
    onOptionSelected: (T) -> Unit,
    placeholder: String,
    optionToString: (T) -> String,
    modifier: Modifier = Modifier
) {
    // Local state to control the expanded status of the dropdown.
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { newValue ->
            expanded = newValue
            Log.d("CategoryDropdown", "Dropdown expanded: $expanded")
        }
    ) {
        TextField(
            value = selectedOption?.let { optionToString(it) } ?: "",
            onValueChange = { /* No-op, since it is read-only */ },
            readOnly = true,
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
                .clickable { expanded = true }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listData.forEach { option ->
                DropdownMenuItem(
                    colors = MenuDefaults.itemColors(
                        textColor = Color.Black
                    ),
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = {
                        Text(text = optionToString(option))
                    }
                )
            }
        }
    }
}