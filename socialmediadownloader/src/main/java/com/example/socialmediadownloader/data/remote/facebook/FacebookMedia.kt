package com.example.socialmediadownloader.data.remote.facebook

import kotlinx.serialization.Serializable

@Serializable
data class FacebookMedia(
    val audioAvailable: Boolean?=null,
    val cached: Boolean?=null,
    val chunked: Boolean?=null,
    val extension: String?=null,
    val formattedSize: String?=null,
    val quality: String?=null,
    val requiresRendering: Boolean?=null,
    val size: Int?=null,
    val url: String?=null,
    val videoAvailable: Boolean?=null
)