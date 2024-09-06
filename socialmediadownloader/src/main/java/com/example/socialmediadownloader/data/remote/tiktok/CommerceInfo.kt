package com.example.socialmediadownloader.data.remote.tiktok
import kotlinx.serialization.Serializable

@Serializable
data class CommerceInfo(
    val adv_promotable: Boolean?=null,
    val auction_ad_invited: Boolean?=null,
    val branded_content_type: Int?=null,
    val with_comment_filter_words: Boolean?=null
)