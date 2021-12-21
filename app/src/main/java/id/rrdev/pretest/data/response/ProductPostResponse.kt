package id.rrdev.pretest.data.response


import com.google.gson.annotations.SerializedName

data class ProductPostResponse(
    @SerializedName("data")
    val data: DataProductPost?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("success")
    val success: Boolean?
)

data class DataProductPost(
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