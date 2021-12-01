package id.rrdev.pretest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.rrdev.pretest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {
            btnPenjumlahan.setOnClickListener {
                startActivity(Intent(this@MainActivity, AmountActivity::class.java))
            }
            btnList.setOnClickListener {
                startActivity(Intent(this@MainActivity, ListActivity::class.java))
            }
        }
    }
}