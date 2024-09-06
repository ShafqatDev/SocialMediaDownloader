package com.example.socialmediadownloader.data.remote.linkedin

import kotlinx.serialization.Serializable

@Serializable
data class LinkedIn(
    val link: String?=null,
    val text: String?=null,
    val title: String?=null
)