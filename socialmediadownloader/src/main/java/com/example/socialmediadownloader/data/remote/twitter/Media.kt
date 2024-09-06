package com.example.socialmediadownloader.data.remote.twitter

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val videos: List<Video>
)