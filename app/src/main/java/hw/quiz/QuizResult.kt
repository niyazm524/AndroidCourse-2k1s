package hw.quiz

import android.util.Log

enum class QuizResult {
    Lisp, Java, Kotlin, Swift, JavaScript;

    fun огласитьРезультаты(результаты: (резы: String) -> Unit) {
        результаты(this.name)
    }

    fun огласитьРезультатыВЛог() {
        Log.d("quiz", this.name)
    }

}
