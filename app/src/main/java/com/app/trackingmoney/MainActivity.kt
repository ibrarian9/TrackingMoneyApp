package com.app.trackingmoney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.trackingmoney.ui.theme.TrackingMoneyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrackingMoneyTheme(darkTheme = false) {
                OnBoardingPage()
            }
        }
    }
}

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Manage your",
                color = Color.White,
                fontSize = 35.sp,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "finance easily",
                color = Color.White,
                fontSize = 35.sp,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Effortlessly track your income and expenses and achieve your financial goals.",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Button(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 20.dp),
                onClick = { /* TODO: handle click event, or leave empty for now */ },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Start",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    OnBoardingPage()
}