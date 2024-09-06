package com.example.socialmediadownloader.data.remote.instagram

import kotlinx.serialization.Serializable

@Serializable
data class MediaUrl(
    val url: String? = null,
    val urlWrapped: String? = null,
    val urlDownloadable: String? = null,
    val type: String? = null,
    val ext: String? = null
)
@Serializable
data class Comment(
    val text: String? = null, val username: String? = null
)
@Serializable
data class MetaData(
    val title: String? = null,
    val source: String? = null,
    val shortcode: String? = null,
    val commentCount: Int? = null,
    val likeCount: Int? = null,
    val takenAt: Long? = null,
    val comments: List<Comment>? = null
)

@Serializable
data class InstagramData(
    val url: List<MediaUrl>? = null,
    val meta: MetaData? = null,
    val thumb: String? = null,
    val pictureUrlWrapped: String? = null,
    val service: String? = null
)