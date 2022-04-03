package test.contact_number.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @Expose
    @SerializedName("bp")
    val bs: String
) : Parcelable
