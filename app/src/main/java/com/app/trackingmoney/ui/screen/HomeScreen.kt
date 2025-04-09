package com.app.trackingmoney.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.trackingmoney.ui.common.BalanceCommon
import ir.ehsannarmani.compose_charts.RowChart
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues
) {
    Column(
        modifier = modifier.fillMaxSize()
            .background(color = Color.Black)
            .padding(paddingValues = innerPadding)
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
            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                text = "Statistics",
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.size(10.dp))
            RowChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 22.dp),
                data = remember {
                    listOf(
                        Bars(
                            label = "Jan",
                            values = listOf(
                                Bars.Data(
                                    label = "Linux",
                                    value = 50.0,
                                    color = Brush.verticalGradient(
                                        colors = listOf(Color.Blue, Color.Cyan)
                                    )
                                ),
                                Bars.Data(
                                    label = "Windows",
                                    value = 70.0,
                                    color = SolidColor(Color.Red)
                                )
                            )
                        ),
                        Bars(
                            label = "Feb",
                            values = listOf(
                                Bars.Data(
                                    label = "Linux",
                                    value = 80.0,
                                    color = Brush.verticalGradient(
                                        colors = listOf(Color.Blue, Color.Cyan)
                                    )
                                ),
                                Bars.Data(
                                    label = "Windows",
                                    value = 60.0,
                                    color = SolidColor(Color.Red)
                                )
                            )
                        )
                    )
                },
                barProperties = BarProperties(
                    cornerRadius = Bars.Data.Radius.Rectangle(topRight = 6.dp, topLeft = 6.dp),
                    spacing = 3.dp,
                    thickness = 20.dp
                ),
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(innerPadding = PaddingValues(20.dp))
}