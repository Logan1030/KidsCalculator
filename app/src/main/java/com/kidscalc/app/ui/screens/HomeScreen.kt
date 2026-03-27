package com.kidscalc.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kidscalc.app.ui.theme.KidsOrange

@Composable
fun HomeScreen(onStartQuiz: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "小小计算器",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = KidsOrange
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "20以内减法练习",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = onStartQuiz,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = KidsOrange)
        ) {
            Text(
                text = "开始练习",
                fontSize = 24.sp
            )
        }
    }
}