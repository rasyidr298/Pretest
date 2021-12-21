package id.rrdev.pretest.data.repository

import id.rrdev.pretest.data.network.MyApi
import id.rrdev.pretest.data.network.SafeApiRequest
import id.rrdev.pretest.data.response.AuthResponse
import id.rrdev.pretest.data.response.ProductResponse
import okhttp3.RequestBody

class AppRepository(private val api: MyApi): SafeApiRequest() {

    suspend fun userLogin(dataLogin: HashMap<String, RequestBody>): AuthResponse {
        return apiRequest { api.authLogin(dataLogin) }
    }

    suspend fun getProduct(): ProductResponse {
        return apiRequest { api.getProduct() }
    }
//
//    suspend fun registerUser(dataRegister: HashMap<String, RequestBody>): AuthResponse {
//        return apiRequest { api.authRegister(dataRegister) }
//    }
//
//    suspend fun submitAvatar(nik: RequestBody, avatar: MultipartBody.Part): AuthResponse {
//        return apiRequest { api.submitAvatar(nik, avatar) }
//    }
//
//    suspend fun genBarcode(nik: String): GenerateBarcodeResponse {
//        return apiRequest { api.generateBarcode(nik) }
//    }
//
//    suspend fun verifEmail(verifEmail: HashMap<String, RequestBody>): VerifEmail {
//        return apiRequest { api.verifEmail(verifEmail) }
//    }
//
//    suspend fun verifCodeEmail(user_id: String, verification_code: String): VerifCodeEmail {
//        return apiRequest { api.verifCodeEmail(user_id, verification_code) }
//    }
//
//    suspend fun updatePassword(updatePassword: HashMap<String, RequestBody>): UpdatePasswordResponse {
//        return apiRequest { api.updatePassword(updatePassword) }
//    }
}