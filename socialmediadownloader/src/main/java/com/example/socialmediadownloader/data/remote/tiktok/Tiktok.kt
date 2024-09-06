package com.example.socialmediadownloader.data.remote.tiktok

import kotlinx.serialization.Serializable

@Serializable
data class Tiktok(
    val code: Int?=null,
    val `data`: Data?=null,
    val msg: String?=null,
    val processed_time: Double?=null
)