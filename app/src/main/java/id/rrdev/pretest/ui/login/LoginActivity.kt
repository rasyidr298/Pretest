package id.rrdev.pretest.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import id.rrdev.pretest.MyApp.Companion.prefManager
import id.rrdev.pretest.databinding.ActivityLoginBinding
import id.rrdev.pretest.ui.HomeActivity
import id.rrdev.pretest.utils.*
import okhttp3.RequestBody

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private val mapLogin = HashMap<String, RequestBody>()
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        observeData()
    }

    private fun initView() {
        viewModel = AuthViewModel(this)

        if (prefManager.getAuthData()?.token != null) {
            intentToHome()
        }

        binding.btnLogin.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        with(binding) {
            listOf<View>(
                tilNik, tilPassword
            ).clearErrorInputLayout()

            if (etEmail.text.isNullOrEmpty()) {
                tilNik.error = "tidak boleh kosong"
                return
            }

            if (etPassword.text.isNullOrEmpty()) {
                tilPassword.error = "tidak boleh kosong"
                return
            }

            val nik = etEmail.text.toString()
            val password = etPassword.text.toString()

            mapLogin["email"] = nik.toMultipartForm()
            mapLogin["password"] = password.toMultipartForm()

            viewModel.loginUser(mapLogin)
        }
    }

    private fun observeData() {
        viewModel.state.observe(this, { result ->
            when (result) {
                is AuthViewModel.LoginState.Succes -> {
                    binding.progress.hide()
                    prefManager.saveAuthData(result.user.data!!)
                    intentToHome()
                }

                is AuthViewModel.LoginState.Error -> {
                    binding.progress.hide()
                    toast("Username dan Password Salah..")
                }
                is AuthViewModel.LoginState.Loading -> {
                    binding.progress.show()
                }
            }
        })
    }

    private fun intentToHome() {
        Intent(this, HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }
}