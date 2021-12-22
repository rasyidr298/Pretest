package id.rrdev.pretest.ui.transaction

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.rrdev.pretest.MyApp.Companion.prefManager
import id.rrdev.pretest.databinding.FragmentTransactionPayBinding

class TransactionPayFragment : Fragment() {

    private var _fragment: FragmentTransactionPayBinding? = null
    private val binding get() = _fragment as FragmentTransactionPayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _fragment = FragmentTransactionPayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        with(binding) {
            val totalPrice = prefManager.spTotalPrice

            etName.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {}
                override fun afterTextChanged(s: Editable?) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s!!.isNotEmpty()) {
                        tvPenerimaan.text = (s.toString().toInt() - totalPrice).toString()
                    }else {
                        tvPenerimaan.text = (0 - totalPrice).toString()
                    }
                }
            })

            tvTotal.text = totalPrice.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragment = null
    }
}