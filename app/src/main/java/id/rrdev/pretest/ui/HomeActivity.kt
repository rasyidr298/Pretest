package id.rrdev.pretest.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import id.rrdev.pretest.MyApp.Companion.prefManager
import id.rrdev.pretest.R
import id.rrdev.pretest.databinding.ActivityHomeBinding
import id.rrdev.pretest.ui.about.AboutFragment
import id.rrdev.pretest.ui.login.LoginActivity
import id.rrdev.pretest.ui.product.ProductFragment
import id.rrdev.pretest.ui.report.ReportFragment
import id.rrdev.pretest.ui.transaction.TransactionFragment

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        navigationChange(ProductFragment())

        binding.btnLogout.setOnClickListener {
            prefManager.clearAllPref()
            Intent(this, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

        binding.bottomNavigationContainer.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.menu_product -> {
                    navigationChange(ProductFragment())
                }
                R.id.menu_transaction  -> {
                    navigationChange(TransactionFragment())
                }
                R.id.menu_report  -> {
                    navigationChange(ReportFragment())
                }
                R.id.menu_about  -> {
                    navigationChange(AboutFragment())
                }
            }
            false
        }
    }

    private fun navigationChange(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}