package test.contact_number.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geolocation(
    @Expose
    @SerializedName("lat")
    val lat: Float,
    @Expose
    @SerializedName("lng")
    val lng: Float
) : Parcelable
