package com.example.socialmediadownloader.data.remote.twitter

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val avatar_color: String?=null,
    val avatar_url: String?=null,
    val banner_url: String?=null,
    val id: String?=null,
    val name: String?=null,
    val screen_name: String?=null
)