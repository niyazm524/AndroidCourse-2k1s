package ml.bimdev.baseapp.network.response


import com.google.gson.annotations.SerializedName

data class WeatherLocationsResponse(
    @SerializedName("cod")
    val cod: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("list")
    val list: List<WeatherLocation>,
    @SerializedName("message")
    val message: String
)
