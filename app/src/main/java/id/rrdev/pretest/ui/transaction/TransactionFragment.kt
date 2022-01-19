package id.rrdev.pretest.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import id.rrdev.pretest.MyApp.Companion.prefManager
import id.rrdev.pretest.R
import id.rrdev.pretest.databinding.FragmentTransactionBinding
import id.rrdev.pretest.ui.adapter.TransactionAdapter
import id.rrdev.pretest.ui.product.ProductInputDialog
import id.rrdev.pretest.ui.product.ProductViewModel
import id.rrdev.pretest.utils.*
import org.koin.android.viewmodel.ext.android.viewModel

class TransactionFragment : Fragment(), OnItemClicked {

    private val viewModel: ProductViewModel by viewModel()
    lateinit var adapter: TransactionAdapter

    var totalPrice = 0

    private var _fragment: FragmentTransactionBinding? = null
    private val binding get() = _fragment as FragmentTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _fragment = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeData()
    }

    private fun initView() {
        adapter = TransactionAdapter(this)

        with(binding.rvProduct){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@TransactionFragment.adapter
        }

        viewModel.getProduct()

        with(binding) {
            btnAdd.setOnClickListener {
                prefManager.spTotalPrice = totalPrice
                TransactionPayDialog.build() {}.show(requireFragmentManager(), tag(requireContext()))
            }
        }
    }

    private fun observeData() {
        viewModel.state.observe(viewLifecycleOwner, { result ->
            when (result) {
                is ProductViewModel.ProductState.Succes -> {
                    this.adapter.addList(result.product.data!!)
                    binding.let {
                        it.progress.hide()

                        //if empty
                        if (result.product.data.isNullOrEmpty()) {
                            it.lottie.setAnimation("empty.json")
                            it.lottie.show()
                        }
                    }
                }

                is ProductViewModel.ProductState.Error -> {
                    binding.let {
                        it.progress.hide()
                        activity?.toast(result.message)
                        it.lottie.setAnimation("error.json")
                        it.lottie.show()
                    }
                }
                is ProductViewModel.ProductState.Loading -> {
                    binding.progress.show()
                }
            }
        })
    }

    override fun onEventClick(data: Int) {
        super.onEventClick(data)

        for (i in 1 until adapter.list.size) {
            totalPrice += (adapter.list[i].harga!!.toInt() * data)
        }
        binding.tvTotal.text = "Rp. $totalPrice"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragment = null
    }
}