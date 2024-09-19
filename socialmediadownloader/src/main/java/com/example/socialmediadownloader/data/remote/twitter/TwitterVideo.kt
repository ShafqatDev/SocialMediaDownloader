package com.example.socialmediadownloader.data.remote.twitter

import kotlinx.serialization.Serializable

@Serializable
data class TwitterVideo(
    val duration: Double?=null,
    val thumbnail_url: String?=null,
    val type: String?=null,
    val video_urls: List<TwitterVideoUrl>?=null
)