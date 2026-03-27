package com.kidscalc.app.viewmodel

import androidx.lifecycle.ViewModel
import com.kidscalc.app.data.QuizEngine
import com.kidscalc.app.data.QuizQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class QuizUiState(
    val currentQuestion: QuizQuestion? = null,
    val questionNumber: Int = 0,
    val totalQuestions: Int = 10,
    val correctCount: Int = 0,
    val userAnswer: String = "",
    val isCorrect: Boolean? = null,
    val showResult: Boolean = false,
    val feedbackMessage: String = ""
)

class QuizViewModel : ViewModel() {
    private val quizEngine = QuizEngine()

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        startQuiz()
    }

    fun startQuiz() {
        _uiState.value = QuizUiState(
            currentQuestion = quizEngine.generateQuestion(),
            questionNumber = 1
        )
    }

    fun updateAnswer(answer: String) {
        _uiState.value = _uiState.value.copy(userAnswer = answer)
    }

    fun submitAnswer() {
        val currentState = _uiState.value
        val currentQuestion = currentState.currentQuestion ?: return
        val userAnswer = currentState.userAnswer.toIntOrNull()

        if (userAnswer == null) {
            _uiState.value = currentState.copy(feedbackMessage = "请输入数字")
            return
        }

        val isCorrect = quizEngine.checkAnswer(currentQuestion, userAnswer)
        val feedback = if (isCorrect) {
            "太棒了！🎉"
        } else {
            "答案是 ${currentQuestion.answer} 哦~ 💪"
        }

        _uiState.value = currentState.copy(
            isCorrect = isCorrect,
            feedbackMessage = feedback,
            correctCount = if (isCorrect) currentState.correctCount + 1 else currentState.correctCount
        )
    }

    fun nextQuestion() {
        val currentState = _uiState.value
        if (currentState.questionNumber >= currentState.totalQuestions) {
            _uiState.value = currentState.copy(showResult = true)
        } else {
            _uiState.value = currentState.copy(
                currentQuestion = quizEngine.generateQuestion(),
                questionNumber = currentState.questionNumber + 1,
                userAnswer = "",
                isCorrect = null,
                feedbackMessage = ""
            )
        }
    }

    fun restartQuiz() {
        startQuiz()
    }
}