package com.example.socialmediadownloader.data.remote.tiktok
import kotlinx.serialization.Serializable

@Serializable
data class TiktokAuthor(
    val avatar: String?=null,
    val id: String?=null,
    val nickname: String?=null,
    val unique_id: String?=null
)