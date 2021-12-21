package id.rrdev.pretest.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.rrdev.pretest.R
import id.rrdev.pretest.databinding.FragmentProductBinding
import id.rrdev.pretest.databinding.FragmentReportBinding

class ProductFragment : Fragment() {

    private var _fragment: FragmentProductBinding? = null
    private val binding get() = _fragment as FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _fragment = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragment = null
    }
}