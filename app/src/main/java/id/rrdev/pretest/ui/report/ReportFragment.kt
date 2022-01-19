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
import id.rrdev.pretest.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel

class ReportFragment : Fragment() {

    private val viewModel: ReportViewModel by viewModel()
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

    override fun onPause() {
        super.onPause()
        binding.mShimmerViewContainer.stopShimmer()
    }

    private fun initView() {
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
                    var total = 0
                    result.product.data.map {
                        total += it.total?.toInt()!!
                    }

                    binding.let {
                        it.tvTotal.text = "Rp. $total"

                        //if empty
                        if (result.product.data.isNullOrEmpty()) {
                            it.lottie.setAnimation("empty.json")
                            it.lottie.show()
                        }

                        //shimmer
                        it.mShimmerViewContainer.hide()
                        it.mShimmerViewContainer.stopShimmer()
                    }
                }

                is ReportViewModel.ReportState.Error -> {
                    binding.let {
                        activity?.toast(result.message)
                        it.lottie.setAnimation("error.json")
                        it.lottie.show()

                        //shimmer
                        it.mShimmerViewContainer.hide()
                        it.mShimmerViewContainer.stopShimmer()
                    }
                }
                is ReportViewModel.ReportState.Loading -> {
                    binding.mShimmerViewContainer.let {
                        //shimmer
                        it.show()
                        it.showShimmer(true)
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragment = null
    }
}