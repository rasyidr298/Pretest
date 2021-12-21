package id.rrdev.pretest.ui.product

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.rrdev.pretest.MyApp.Companion.prefManager
import id.rrdev.pretest.R
import id.rrdev.pretest.databinding.FragmentProductBinding
import id.rrdev.pretest.databinding.FragmentProductInputBinding
import id.rrdev.pretest.utils.*
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class ProductInputFragment : Fragment() {

    lateinit var viewModel: ProductViewModel
    private val mapProduct = HashMap<String, RequestBody>()

    private var _fragment: FragmentProductInputBinding? = null
    private val binding get() = _fragment as FragmentProductInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _fragment = FragmentProductInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeData()
    }

    private fun setupView() {
        viewModel = ProductViewModel(requireContext())

        with(binding) {
            btnSave.setOnClickListener {
                doPostProduct()
            }
        }
    }

    private fun doPostProduct() {
        with(binding) {
            listOf<View>(
                tilNik, tilPassword
            ).clearErrorInputLayout()

            if (etName.text.isNullOrEmpty()) {
                tilNik.error = "tidak boleh kosong"
                return
            }

            if (etPrice.text.isNullOrEmpty()) {
                tilPassword.error = "tidak boleh kosong"
                return
            }

            val name = etName.text.toString()
            val price = etPrice.text.toString()

            mapProduct["nama"] = name.toMultipartForm()
            mapProduct["harga"] = price.toMultipartForm()
            mapProduct["admin_id"] = prefManager.getAuthData()?.admin?.id!!.toMultipartForm()
            mapProduct["stock"] = "1".toMultipartForm()

            viewModel.postProduct(mapProduct)
        }
    }

    private fun observeData() {
        viewModel.state.observe(this, { result ->
            when (result) {
                is ProductViewModel.ProductState.SuccesPost -> {
                    binding.progress.hide()
                    context?.toast("Success..")
                }

                is ProductViewModel.ProductState.Error -> {
                    binding.progress.hide()
                    context?.toast("Gagal, Ulangii..")
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