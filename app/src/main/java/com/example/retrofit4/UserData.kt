package com.example.retrofit4


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class UserData(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total")
    val total: Int
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("firstName")
        val firstName: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("lastName")
        val lastName: String,
        @SerializedName("picture")
        val picture: String,
        @SerializedName("title")
        val title: String
    ) : Parcelable
}