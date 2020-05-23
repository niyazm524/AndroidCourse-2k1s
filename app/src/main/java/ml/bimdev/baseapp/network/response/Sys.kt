package ml.bimdev.baseapp.network.response


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country")
    val country: String
)
