package id.rrdev.pretest.data.response


import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("data")
    val authData: AuthData?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)

data class AuthData(
    @SerializedName("email")
    val email: String?,
    @SerializedName("institusi")
    val instansi: String?,
    @SerializedName("jabatan")
    val jabatan: String?,
    @SerializedName("name")
    val nama: String?,
    @SerializedName("nik")
    val nik: String?,
    @SerializedName("telephone")
    val telephone: String?,
    @SerializedName("avatar")
    val avatar: String?,
)