package id.rrdev.pretest.data.response


import com.google.gson.annotations.SerializedName

data class ProductPostResponse(
    @SerializedName("data")
    val data: DataProduct?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("success")
    val success: Boolean?
)