package com.example.socialmediadownloader.data.remote.twitter

import kotlinx.serialization.Serializable

@Serializable
data class TwitterMedia(
    val twitterVideos: List<TwitterVideo>
)