package id.rrdev.pretest.data.response


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("data")
    val data: List<DataProduct>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("success")
    val success: Boolean?
)

data class DataProduct(
    @SerializedName("admin_id")
    val adminId: String?,
    @SerializedName("harga")
    val harga: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("nama")
    val nama: String?,
    @SerializedName("stock")
    val stock: String?
)