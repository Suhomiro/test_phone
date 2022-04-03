package test.contact_number.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "users")
@Parcelize
data class Users (

    @PrimaryKey
    @Expose
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "name")
    @Expose
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "username")
    @Expose
    @SerializedName("username")
    val userName: String,

    @ColumnInfo(name = "email")
    @Expose
    @SerializedName("email")
    val email: String,

    @Embedded(prefix = "address")
    @Expose
    @SerializedName("address")
    val address: Address,

    @ColumnInfo(name = "phone")
    @Expose
    @SerializedName("phone")
    val phone: String,

    @ColumnInfo(name = "websites")
    @Expose
    @SerializedName("websites")
    val website: String,

    @Embedded(prefix = "company")
    @Expose
    @SerializedName("company")
    val company: Company
        ) : Parcelable
