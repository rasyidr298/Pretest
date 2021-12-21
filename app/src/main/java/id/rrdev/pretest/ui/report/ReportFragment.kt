package id.rrdev.pretest.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.rrdev.pretest.databinding.FragmentReportBinding

class ReportFragment : Fragment() {

    private var _fragment: FragmentReportBinding? = null
    private val binding get() = _fragment as FragmentReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _fragment = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragment = null
    }
}