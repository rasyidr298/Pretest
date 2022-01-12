package id.rrdev.pretest.data.repository

import id.rrdev.pretest.data.network.MyApi
import id.rrdev.pretest.data.network.SafeApiRequest
import id.rrdev.pretest.data.response.*
import okhttp3.RequestBody

class AppRepository(private val api: MyApi): SafeApiRequest() {

    suspend fun userLogin(dataLogin: HashMap<String, RequestBody>): AuthResponse {
        return apiRequest { api.authLogin(dataLogin) }
    }

    suspend fun getProduct(): ProductResponse {
        return apiRequest { api.getProduct() }
    }

    suspend fun getTransaction(): TransactionResponse {
        return apiRequest { api.getTransaction() }
    }

    suspend fun postProduct(dataProduct: HashMap<String, RequestBody>): ProductPostResponse {
        return apiRequest { api.postProduct(dataProduct) }
    }

    suspend fun deleteProduct(productId: String): ProductResponse {
        return apiRequest { api.deleteProduct(productId) }
    }

    suspend fun updateProduct(productId: String, dataProduct: HashMap<String, RequestBody>): ProductPostResponse {
        return apiRequest { api.updateProduct(productId, dataProduct) }
    }
}