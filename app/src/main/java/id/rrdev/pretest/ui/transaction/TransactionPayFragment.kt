package id.rrdev.pretest.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun onDestroyView() {
        super.onDestroyView()
        _fragment = null
    }
}