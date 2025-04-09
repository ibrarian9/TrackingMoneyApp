package com.app.trackingmoney.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BoxCommon(
    modifier: Modifier = Modifier,
    name: String,
    value: Int = 0,
    image: ImageVector,
    colorIcon: Color
) {
    Row(
        modifier = modifier
            .background(
                color = Color.DarkGray,
                shape = ShapeDefaults.Large
            )
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = image,
            tint = colorIcon,
            contentDescription = "",
        )
        Spacer(modifier = Modifier.size(10.dp))
        Column {
            Text(
                text = name,
                color = Color.White,
                fontSize = 15.sp,
            )
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = value.toString(),
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}