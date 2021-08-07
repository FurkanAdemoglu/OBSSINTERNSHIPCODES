package com.example.apimvvm.model

import com.google.gson.annotations.SerializedName

data class Show(
    @SerializedName("name") val name:String?,
    @SerializedName("genres") val genres: List<String?>?,
    @SerializedName("image") val image: Image?

)
