package id.rrdev.pretest.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.rrdev.pretest.databinding.FragmentReportBinding
import id.rrdev.pretest.ui.adapter.ReportAdapter
import id.rrdev.pretest.utils.hide
import id.rrdev.pretest.utils.show

class ReportFragment : Fragment() {

    lateinit var viewModel: ReportViewModel
    lateinit var adapter: ReportAdapter

    private var _fragment: FragmentReportBinding? = null
    private val binding get() = _fragment as FragmentReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _fragment = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeData()
    }

    private fun initView() {
        viewModel = ReportViewModel(requireContext())
        adapter = ReportAdapter()

        with(binding.rvProduct){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ReportFragment.adapter
        }

        viewModel.getTransaction()
    }

    private fun observeData() {
        viewModel.state.observe(viewLifecycleOwner, { result ->
            when (result) {
                is ReportViewModel.ReportState.Succes -> {
                    this.adapter.addList(result.product.data!!)
                    binding.progress.hide()
                }

                is ReportViewModel.ReportState.Error -> {
                    binding.progress.hide()
                }
                is ReportViewModel.ReportState.Loading -> {
                    binding.progress.show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragment = null
    }
}