package id.rrdev.pretest.ui.report

import android.content.Context
import id.rrdev.pretest.data.network.MyApi
import id.rrdev.pretest.data.network.NetworkConnectionInterceptor
import id.rrdev.pretest.data.repository.AppRepository
import id.rrdev.pretest.data.response.ProductPostResponse
import id.rrdev.pretest.data.response.ProductResponse
import id.rrdev.pretest.data.response.TransactionData
import id.rrdev.pretest.data.response.TransactionResponse
import id.rrdev.pretest.utils.ApiException
import id.rrdev.pretest.utils.BaseViewModel
import id.rrdev.pretest.utils.Coroutines
import id.rrdev.pretest.utils.NoInternetException
import okhttp3.RequestBody
import java.io.IOException


class ReportViewModel(context: Context): BaseViewModel<ReportViewModel.ReportState>() {

    private val networkConnectionInterceptor = NetworkConnectionInterceptor(context)
    private val api = MyApi(networkConnectionInterceptor)
    private val repository = AppRepository(api)

    sealed class ReportState{
        data class Loading(val isLoading:Boolean) : ReportState()
        data class Succes(val product : TransactionResponse) : ReportState()
        data class Error(val message :String) : ReportState()
    }

    fun getTransaction() {
        Coroutines.main {
            _state.value= (ReportState.Loading(true))

            try {
                val barcodeResponse = repository.getTransaction()
                barcodeResponse.let {
                    _state.value= (ReportState.Succes(it))
                    return@main
                }

            } catch (e: ApiException) {
                _state.value= (ReportState.Error(e.message.toString()))
            } catch (e: NoInternetException) {
                _state.value= (ReportState.Error(e.message.toString()))
            }catch (e: IOException){
                _state.value= (ReportState.Error(e.message.toString()))
            }
        }
    }
}