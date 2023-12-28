package io.github.hadyahmed00.movieapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id : Int ?,

    @SerializedName("title")
    val title : String?,

    @SerializedName("poster")
    val poster : String?,

    @SerializedName("rating")
    val rating : Double?,

    @SerializedName("overview")
    val overview : String?


) : Parcelable{

}