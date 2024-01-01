package io.github.hadyahmed00.movieapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.github.hadyahmed00.movieapp.util.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Constants.MOVIE_TABLE_NAME)
data class Movie(
    @PrimaryKey(autoGenerate = false)
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


) : Parcelable

