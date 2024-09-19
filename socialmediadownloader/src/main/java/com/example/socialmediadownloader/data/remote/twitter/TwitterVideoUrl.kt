package com.example.socialmediadownloader.data.remote.twitter

import kotlinx.serialization.Serializable

@Serializable
data class TwitterVideoUrl(
    val bitrate: Int?=null,
    val content_type: String?=null,
    val url: String?=null
)