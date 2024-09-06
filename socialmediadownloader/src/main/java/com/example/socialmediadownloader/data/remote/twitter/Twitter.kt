package com.example.socialmediadownloader.data.remote.twitter

import kotlinx.serialization.Serializable

@Serializable
data class Twitter(
    val code: Int?=null,
    val message: String?=null,
    val tweet: Tweet? = null
)