package com.kidscalc.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kidscalc.app.ui.theme.KidsGreen
import com.kidscalc.app.ui.theme.KidsRed
import com.kidscalc.app.viewmodel.QuizUiState
import com.kidscalc.app.viewmodel.QuizViewModel

@Composable
fun QuizScreen(
    viewModel: QuizViewModel,
    onShowResult: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 进度指示
        Text(
            text = "第 ${uiState.questionNumber} / ${uiState.totalQuestions} 题",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 算式显示
        uiState.currentQuestion?.let { question ->
            Text(
                text = question.expression,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 答案输入
        OutlinedTextField(
            value = uiState.userAnswer,
            onValueChange = { viewModel.updateAnswer(it) },
            label = { Text("你的答案") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = LocalTextStyle.current.copy(fontSize = 24.sp),
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.isCorrect == null
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 确认按钮
        Button(
            onClick = { viewModel.submitAnswer() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = uiState.userAnswer.isNotEmpty() && uiState.isCorrect == null
        ) {
            Text("确认", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 反馈显示
        if (uiState.feedbackMessage.isNotEmpty()) {
            FeedbackCard(uiState)
        }

        // 下一题按钮
        if (uiState.isCorrect != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (uiState.questionNumber >= uiState.totalQuestions) {
                        onShowResult()
                    } else {
                        viewModel.nextQuestion()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = if (uiState.questionNumber >= uiState.totalQuestions) "查看结果" else "下一题",
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
private fun FeedbackCard(uiState: QuizUiState) {
    val backgroundColor = if (uiState.isCorrect == true) KidsGreen else KidsRed
    val emoji = if (uiState.isCorrect == true) "✓" else "×"

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Text(
            text = "$emoji ${uiState.feedbackMessage}",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}