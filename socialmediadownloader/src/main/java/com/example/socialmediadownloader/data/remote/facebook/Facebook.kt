package com.example.socialmediadownloader.data.remote.facebook

import kotlinx.serialization.Serializable

@Serializable
data class Facebook(
    val duration: String?=null,
    val medias: List<Media>?=null,
    val sid: String?=null,
    val source: String?=null,
    val thumbnail: String?=null,
    val title: String?=null,
    val url: String?=null
)