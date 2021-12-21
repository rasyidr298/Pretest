package id.rrdev.pretest.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.rrdev.pretest.R
import id.rrdev.pretest.databinding.ActivityHomeBinding
import id.rrdev.pretest.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}