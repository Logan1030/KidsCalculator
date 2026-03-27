package com.kidscalc.app.data

import kotlin.random.Random

data class QuizQuestion(
    val minuend: Int,    // 被减数
    val subtrahend: Int, // 减数
    val answer: Int      // 答案
) {
    val expression: String
        get() = "$minuend - $subtrahend = ?"
}

class QuizEngine {
    private val random = Random

    fun generateQuestion(): QuizQuestion {
        // 生成 1-20 的被减数
        val minuend = random.nextInt(1, 21)
        // 生成减数，确保结果 >= 0
        val subtrahend = random.nextInt(0, minuend + 1)
        return QuizQuestion(minuend, subtrahend, minuend - subtrahend)
    }

    fun checkAnswer(question: QuizQuestion, userAnswer: Int): Boolean {
        return question.answer == userAnswer
    }
}