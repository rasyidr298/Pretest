package id.rrdev.pretest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.rrdev.pretest.databinding.ActivityAmountBinding
import id.rrdev.pretest.databinding.ActivityMainBinding

class AmountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAmountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAmountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {
            btnProcess.setOnClickListener {
                val number1 = etNumber1.text.toString()
                val number2 = etNumber2.text.toString()

                if (number1.isEmpty() or number2.isEmpty()) {
                    Toast.makeText(this@AmountActivity, "harus terisi semua", Toast.LENGTH_SHORT).show()
                }else {
                    val result = (number1.toInt() + number2.toInt())
                    tvResult.text = "Hasil \n$result"
                }
            }
        }
    }
}