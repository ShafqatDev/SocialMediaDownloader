package com.example.socialmediadownloader.data.remote.twitter

import kotlinx.serialization.Serializable

@Serializable
data class VideoUrl(
    val bitrate: Int?=null,
    val content_type: String?=null,
    val url: String?=null
)