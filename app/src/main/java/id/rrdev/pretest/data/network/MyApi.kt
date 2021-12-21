package id.rrdev.pretest.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import id.rrdev.pretest.MyApp.Companion.prefManager
import id.rrdev.pretest.data.response.AuthResponse
import id.rrdev.pretest.data.response.ProductResponse
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface MyApi {

    @JvmSuppressWildcards
    @Multipart
    @POST("auth/login")
    suspend fun authLogin(
        @PartMap postRegister: Map<String, RequestBody>
    ): Response<AuthResponse>

    @GET("product")
    suspend fun getProduct(
    ): Response<ProductResponse>
//
//    @JvmSuppressWildcards
//    @Multipart
//    @POST("user/register")
//    suspend fun authRegister(
//        @PartMap postRegister: Map<String, RequestBody>
//    ): Response<AuthResponse>
//
//    @Multipart
//    @POST("user/avatar")
//    suspend fun submitAvatar(
//        @Part("nik") nik: RequestBody,
//        @Part avatar: MultipartBody.Part
//    ): Response<AuthResponse>
//
//    @GET("qrcode/generate")
//    suspend fun generateBarcode(
//        @Query("nik") nik: String
//    ): Response<GenerateBarcodeResponse>
//
//    @JvmSuppressWildcards
//    @Multipart
//    @POST("user/send_verify_code")
//    suspend fun verifEmail(
//        @PartMap postVerifEmail: Map<String, RequestBody>
//    ): Response<VerifEmail>
//
//    @GET("user/cek_verify_code")
//    suspend fun verifCodeEmail(
//        @Query("user_id") user_id: String,
//        @Query("verification_code") verif_code: String
//    ): Response<VerifCodeEmail>
//
//    @JvmSuppressWildcards
//    @Multipart
//    @POST("user/update_password")
//    suspend fun updatePassword(
//        @PartMap postUpdatePassword: Map<String, RequestBody>
//    ): Response<UpdatePasswordResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("https://69b6-182-1-77-239.ngrok.io/ci-pcs-rest-api/api/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
               .client(getOkHttpClient(networkConnectionInterceptor))
                .build()
                .create(MyApi::class.java)
        }


        private fun getOkHttpClient(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): OkHttpClient {
            val timeOut = 60L
            return OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
               .addInterceptor(networkConnectionInterceptor)
                .addInterceptor {
                    val req = it.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer " + prefManager.spToken)
                        .build()
                    return@addInterceptor it.proceed(req)
                }
                .build()
        }

        private val interceptor = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }
}