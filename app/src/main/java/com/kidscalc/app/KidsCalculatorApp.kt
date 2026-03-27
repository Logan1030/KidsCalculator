package com.kidscalc.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kidscalc.app.ui.screens.HomeScreen
import com.kidscalc.app.ui.screens.QuizScreen
import com.kidscalc.app.ui.screens.ResultScreen
import com.kidscalc.app.ui.theme.KidsCalculatorTheme
import com.kidscalc.app.viewmodel.QuizViewModel

enum class Screen {
    HOME, QUIZ, RESULT
}

@Composable
fun KidsCalculatorApp() {
    KidsCalculatorTheme {
        var currentScreen by remember { mutableStateOf(Screen.HOME) }
        val viewModel: QuizViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()

        when (currentScreen) {
            Screen.HOME -> {
                HomeScreen(
                    onStartQuiz = {
                        viewModel.startQuiz()
                        currentScreen = Screen.QUIZ
                    }
                )
            }
            Screen.QUIZ -> {
                QuizScreen(
                    viewModel = viewModel,
                    onShowResult = { currentScreen = Screen.RESULT }
                )
            }
            Screen.RESULT -> {
                ResultScreen(
                    correctCount = uiState.correctCount,
                    totalCount = uiState.totalQuestions,
                    onRestart = {
                        viewModel.restartQuiz()
                        currentScreen = Screen.QUIZ
                    }
                )
            }
        }
    }
}