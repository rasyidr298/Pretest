package id.rrdev.pretest.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import id.rrdev.pretest.R
import id.rrdev.pretest.databinding.FragmentProductBinding
import id.rrdev.pretest.ui.adapter.ProductAdapter
import id.rrdev.pretest.ui.inputProduct.ProductInputFragment
import id.rrdev.pretest.utils.hide
import id.rrdev.pretest.utils.show

class ProductFragment : Fragment() {

    lateinit var viewModel: ProductViewModel
    lateinit var adapter: ProductAdapter

    private var _fragment: FragmentProductBinding? = null
    private val binding get() = _fragment as FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _fragment = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeData()
    }

    private fun initView() {
        viewModel = ProductViewModel(requireContext())
        adapter = ProductAdapter()

        with(binding.rvProduct){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ProductFragment.adapter
        }

        viewModel.getProduct()

        with(binding) {
            btnAdd.setOnClickListener {
                navigationChange(ProductInputFragment())
            }
        }
    }

    private fun navigationChange(fragment: Fragment) {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    private fun observeData() {
        viewModel.state.observe(viewLifecycleOwner, { result ->
            when (result) {
                is ProductViewModel.ProductState.Succes -> {
                    this.adapter.addList(result.product.data!!)
                    binding.tvTotal.text = result.product.data.size.toString()

                    binding.progress.hide()
                }

                is ProductViewModel.ProductState.Error -> {
                    binding.progress.hide()
                }
                is ProductViewModel.ProductState.Loading -> {
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