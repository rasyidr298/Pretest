package id.rrdev.pretest.ui.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.rrdev.pretest.R
import id.rrdev.pretest.databinding.FragmentTransactionBinding
import id.rrdev.pretest.databinding.FragmentTransactionPayBinding

class TransactionFragment : Fragment() {

    private var _fragment: FragmentTransactionBinding? = null
    private val binding get() = _fragment as FragmentTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _fragment = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragment = null
    }
}