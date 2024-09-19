package com.example.socialmediadownloader.data.remote.instagram

import kotlinx.serialization.Serializable

@Serializable
data class InstagramMediaUrl(
    val url: String? = null,
    val urlWrapped: String? = null,
    val urlDownloadable: String? = null,
    val type: String? = null,
    val ext: String? = null
)
@Serializable
data class InstagramComment(
    val text: String? = null, val username: String? = null
)
@Serializable
data class InstagramMetaData(
    val title: String? = null,
    val source: String? = null,
    val shortcode: String? = null,
    val commentCount: Int? = null,
    val likeCount: Int? = null,
    val takenAt: Long? = null,
    val instagramComments: List<InstagramComment>? = null
)

@Serializable
data class InstagramData(
    val url: List<InstagramMediaUrl>? = null,
    val meta: InstagramMetaData? = null,
    val thumb: String? = null,
    val pictureUrlWrapped: String? = null,
    val service: String? = null
)