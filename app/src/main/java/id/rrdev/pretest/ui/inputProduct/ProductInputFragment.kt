package id.rrdev.pretest.ui.inputProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.rrdev.pretest.R
import id.rrdev.pretest.databinding.FragmentProductBinding
import id.rrdev.pretest.databinding.FragmentProductInputBinding

class ProductInputFragment : Fragment() {

    private var _fragment: FragmentProductInputBinding? = null
    private val binding get() = _fragment as FragmentProductInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _fragment = FragmentProductInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragment = null
    }
}