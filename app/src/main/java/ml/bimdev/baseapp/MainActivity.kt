package ml.bimdev.baseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import hw.quiz.LangChooser

@Suppress("NonAsciiCharacters")
class MainActivity : AppCompatActivity() { override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LangChooser
            .яНовенький
            .понял?.да!!
            .статьСмешариком()
            .привыкМногоПечатать?.нет!!
            .КакНасчетПарадигмы()
            .λλλλλλλλλλλλλλ { строкаТекста -> записать(строкаТекста) }
            .еслиРотВГовне()
            .огласитьРезультаты {резы ->
                записать(резы)
            }
    }

    inline fun записать(строчка: String) {
        Log.i("MainActivity", строчка)
    }
}
