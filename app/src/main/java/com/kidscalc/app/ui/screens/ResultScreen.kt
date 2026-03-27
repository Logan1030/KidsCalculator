package com.kidscalc.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kidscalc.app.ui.theme.KidsGreen
import com.kidscalc.app.ui.theme.KidsOrange

@Composable
fun ResultScreen(
    correctCount: Int,
    totalCount: Int,
    onRestart: () -> Unit
) {
    val percentage = (correctCount * 100) / totalCount

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "练习完成！",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = KidsOrange
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "$correctCount / $totalCount",
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            color = KidsGreen
        )

        Text(
            text = "正确率",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "$percentage%",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = if (percentage >= 70) KidsGreen else KidsOrange
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onRestart,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = KidsOrange)
        ) {
            Text(
                text = "再来一次",
                fontSize = 24.sp
            )
        }
    }
}