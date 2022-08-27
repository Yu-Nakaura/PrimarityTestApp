package app.nakaura.chloe.sosuu

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import app.nakaura.chloe.sosuu.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var random: Random = Random()
    val questions: IntArray = IntArray(QUESTION_COUNT)
    var point: Int = 0
    var answerCount: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }


        for (i in 0 until QUESTION_COUNT) {
            val number = random.nextInt(1000)
            Log.d("Number", "Question$number")
            questions[i] = number
        }

        binding.numberText.text = questions[answerCount].toString()
        binding.numberText.setTextColor(Color.BLACK)


        binding.maruButton.setOnClickListener {
            var answer = true
            val number = questions[answerCount]
            // なにかでわりきれたら素数でない
            for (i in 2 until number) {
                if (number % i == 0) {
                    answer = false
                    Log.d("素数ではない", "$i でわりきれる")
                    break
                }
            }

            if (answer) {
                //素数
                Toast.makeText(this, "正解 $number は素数", Toast.LENGTH_SHORT).show()
                point++
                Log.d("maru", "正解 $number は素数")
            } else {
                //素数ではない
                Toast.makeText(this, "不正解 $number は素数ではない", Toast.LENGTH_SHORT).show()
                Log.d("maru", "不正解 $number は素数ではない")
            }
            answerCount++
            result()
        }

        binding.batsuButton.setOnClickListener {
            var answer = true
            val number = questions[answerCount]
            // なにかでわりきれたら素数でない
            for (i in 2 until number) {
                if (number % i == 0) {
                    answer = false
                    Log.d("素数ではない", "$i でわりきれる")
                    break
                }
            }

            if (answer) {
                //素数
                Toast.makeText(this, "不正解 $number は素数", Toast.LENGTH_SHORT).show()
                Log.d("batsu", "不正解 $number は素数")
            } else {
                //素数ではない
                Toast.makeText(this, "正解 $number は素数でない", Toast.LENGTH_SHORT).show()
                point++
                Log.d("batsu", "正解 $number は素数でない")
            }
            answerCount++
            result()
        }
    }


    fun result(){
        if(answerCount == QUESTION_COUNT) {
            binding.numberText.text = point.toString() + "点"
            binding.numberText.setTextColor(Color.RED)

            point = 0
            answerCount = 0
        }else{
            binding.numberText.text = questions[answerCount].toString()
            binding.numberText.setTextColor(Color.BLACK)
        }
    }

    companion object {
        private const val QUESTION_COUNT: Int = 10
    }
}