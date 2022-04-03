package test.contact_number.model

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    @Expose
    @SerializedName("street")
    val street: String,
    @Expose
    @SerializedName("suite")
    val suite: String,
    @Expose
    @SerializedName("city")
    val city: String,
    @Expose
    @SerializedName("zipcode")
    val zipcode: String,
    @Embedded
    @Expose
    @SerializedName("geo")
    val geolocation: Geolocation
) : Parcelable
