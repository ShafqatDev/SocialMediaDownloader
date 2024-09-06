package com.example.socialmediadownloader.data.remote.linkedin

import kotlinx.serialization.Serializable

@Serializable
data class LinkedInModel(
    val Url: String="",
    val avc: Int=17
)