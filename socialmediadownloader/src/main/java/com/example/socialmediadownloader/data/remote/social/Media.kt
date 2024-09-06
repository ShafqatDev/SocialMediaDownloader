package com.example.socialmediadownloader.data.remote.social

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val audioAvailable: Boolean? = null,
    val cached: Boolean? = null,
    val chunked: Boolean? = null,
    val extension: String? = null,
    val formattedSize: String? = null,
    val quality: String? = null,
    val size: Int? = null,
    val url: String? = null,
    val videoAvailable: Boolean? = null
)