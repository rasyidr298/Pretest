package id.rrdev.pretest.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.rrdev.pretest.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _fragment: FragmentAboutBinding? = null
    private val binding get() = _fragment as FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _fragment = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragment = null
    }
}