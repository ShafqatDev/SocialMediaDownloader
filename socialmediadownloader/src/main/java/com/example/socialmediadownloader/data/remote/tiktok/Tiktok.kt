package com.example.socialmediadownloader.data.remote.tiktok

import kotlinx.serialization.Serializable

@Serializable
data class Tiktok(
    val code: Int?=null,
    val tiktokData: TiktokData?=null,
    val msg: String?=null,
    val processed_time: Double?=null
)