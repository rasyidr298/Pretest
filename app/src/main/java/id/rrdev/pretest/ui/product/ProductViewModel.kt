package id.rrdev.pretest.ui.product

import android.content.Context
import id.rrdev.pretest.data.network.MyApi
import id.rrdev.pretest.data.network.NetworkConnectionInterceptor
import id.rrdev.pretest.data.repository.AppRepository
import id.rrdev.pretest.data.response.ProductPostResponse
import id.rrdev.pretest.data.response.ProductResponse
import id.rrdev.pretest.utils.ApiException
import id.rrdev.pretest.utils.BaseViewModel
import id.rrdev.pretest.utils.Coroutines
import id.rrdev.pretest.utils.NoInternetException
import okhttp3.RequestBody
import java.io.IOException


class ProductViewModel(context: Context): BaseViewModel<ProductViewModel.ProductState>() {

    private val networkConnectionInterceptor = NetworkConnectionInterceptor(context)
    private val api = MyApi(networkConnectionInterceptor)
    private val repository = AppRepository(api)

    sealed class ProductState{
        data class Loading(val isLoading:Boolean) : ProductState()
        data class Succes(val product : ProductResponse) : ProductState()
        data class SuccesPost(val product : ProductPostResponse) : ProductState()
        data class Error(val message :String) : ProductState()
    }

    fun getProduct() {
        Coroutines.main {
            _state.value= (ProductState.Loading(true))

            try {
                val barcodeResponse = repository.getProduct()
                barcodeResponse.let {
                    _state.value= (ProductState.Succes(it))
                    return@main
                }

            } catch (e: ApiException) {
                _state.value= (ProductState.Error(e.message.toString()))
            } catch (e: NoInternetException) {
                _state.value= (ProductState.Error(e.message.toString()))
            }catch (e: IOException){
                _state.value= (ProductState.Error(e.message.toString()))
            }
        }
    }

    fun postProduct(dataProduct: HashMap<String, RequestBody>) {
        Coroutines.main {
            _state.value= (ProductState.Loading(true))

            try {
                val barcodeResponse = repository.postProduct(dataProduct)
                barcodeResponse.let {
                    _state.value= (ProductState.SuccesPost(it))
                    return@main
                }

            } catch (e: ApiException) {
                _state.value= (ProductState.Error(e.message.toString()))
            } catch (e: NoInternetException) {
                _state.value= (ProductState.Error(e.message.toString()))
            }catch (e: IOException){
                _state.value= (ProductState.Error(e.message.toString()))
            }
        }
    }

}