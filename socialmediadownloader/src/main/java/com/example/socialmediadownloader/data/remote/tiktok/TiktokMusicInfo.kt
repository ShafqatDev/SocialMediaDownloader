package com.example.socialmediadownloader.data.remote.tiktok
import kotlinx.serialization.Serializable

@Serializable
data class TiktokMusicInfo(
    val album: String?=null,
    val author: String?=null,
    val cover: String?=null,
    val duration: Int?=null,
    val id: String?=null,
    val original: Boolean?=null,
    val play: String?=null,
    val title: String?=null
)