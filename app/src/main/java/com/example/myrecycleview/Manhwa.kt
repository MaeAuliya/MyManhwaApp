package com.example.myrecycleview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Manhwa(
    val name: String,
    val description: String,
    val photo: String,
    val genre: String,
    val year: String,
    val rate: String
) : Parcelable
