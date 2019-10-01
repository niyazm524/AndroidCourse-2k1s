package hw.quiz

import android.util.Log

fun didYouPassThisQuizAlreadyDanger() {
    """
         ____    _    _   _  ____ _____ ____  
        |  _ \  / \  | \ | |/ ___| ____|  _ \ 
        | | | |/ _ \ |  \| | |  _|  _| | |_) |
        | |_| / ___ \| |\  | |_| | |___|  _ < 
        |____/_/   \_\_| \_|\____|_____|_| \_\
        
     ____  ____   ___ ___ _     _____ ____  ____  
    / ___||  _ \ / _ \_ _| |   | ____|  _ \/ ___| 
    \___ \| |_) | | | | || |   |  _| | |_) \___ \ 
     ___) |  __/| |_| | || |___| |___|  _ < ___) |
    |____/|_|    \___/___|_____|_____|_| \_\____/ 
                  
    """.trimIndent()
}

open class ДаНет<Д, Н>(private val классДа: Class<Д>, private val классНет: Class<Н>) {
    val да: Д? = классДа.newInstance()
    val нет: Н? = классНет.newInstance()
}

object Смешарик {
    var привыкМногоПечатать: Многословность? = Многословность()
}

class ЭтоДляЛиспа {
    operator fun invoke() = ДокажиЧтоЛюбишьЛисп

    object ДокажиЧтоЛюбишьЛисп {
        operator fun invoke() = Раунд3()
        class Раунд3 {
            operator fun invoke(сколькоСкобокНасчитал: IntRange) = QuizResult.Lisp
        }
    }
}

class Многословность
    : ДаНет<Многословность.ЛюблюПоболтать, Многословность.ИногдаДостаточноПарыСлов>
    (ЛюблюПоболтать::class.java, ИногдаДостаточноПарыСлов::class.java) {

    class ЛюблюПоболтать {
        fun блаблаблаТакМногоСловИТакМалоДействий() = QuizResult.Java
    }

    class ИногдаДостаточноПарыСлов {
        inner class КакНасчетПарадигмы {
            val оопЭтоМояЖизнь: ЛюбительООП.Companion = ЛюбительООП

            fun λλλλλλλλλλλλλλ(ConsoleLog: (строкаТекста: String) -> Unit): ФункциАналОчка {
                """
                    __   __              __   __               ____                
                    \ \ / /_ _ _ __ ___  \ \ / /_ _ _ __ ___  |  _ \  __ _ _______ 
                     \ V / _` | '__/ _ \  \ V / _` | '__/ _ \ | | | |/ _` |_  / _ \
                      | | (_| | | |  __/   | | (_| | | |  __/ | |_| | (_| |/ /  __/
                      |_|\__,_|_|  \___|   |_|\__,_|_|  \___| |____/ \__,_/___\___|
                                                              
                """.trimIndent().lines().forEach(ConsoleLog)
                return ФункциАналОчка()
            }
        }
    }
}

class ФункциАналОчка {
    inner class хыСкобочки {
        operator fun invoke(): ЭтоДляЛиспа {
            Log.e("quiz", "О господи держите лиспача")
            return ЭтоДляЛиспа()
        }
    }

    fun еслиРотВГовне(): QuizResult {
        Log.d("quiz", "пахнет жиесером")
        return QuizResult.JavaScript
    }

}

class ЛюбительООП {
    companion object {
        operator fun invoke(ПоследнийВопрос: Char) = ВажныйВыбор()
    }
}

class ВажныйВыбор {
    var стул_с_пиками = QuizResult.Kotlin
    val стул_с_х_ями = QuizResult.Swift
}