package com.example.socialmediadownloader.data.remote.tiktok

import kotlinx.serialization.Serializable

@Serializable
data class TiktokData(
    val ai_dynamic_cover: String?=null,
    val anchors: String?=null,
    val anchors_extras: String?=null,
    val tiktokAuthor: TiktokAuthor?=null,
    val collect_count: Int?=null,
    val comment_count: Int?=null,
    val commerce_info: TiktokCommerceInfo?=null,
    val commercial_video_info: String?=null,
    val cover: String?=null,
    val create_time: Int?=null,
    val digg_count: Int?=null,
    val download_count: Int?=null,
    val duration: Int?=null,
    val hd_size: Int?=null,
    val hdplay: String?=null,
    val id: String?=null,
    val is_ad: Boolean?=null,
    val item_comment_settings: Int?=null,
    val music: String?=null,
    val music_info: TiktokMusicInfo?=null,
    val origin_cover: String?=null,
    val play: String?=null,
    val play_count: Int?=null,
    val region: String?=null,
    val share_count: Int?=null,
    val size: Int?=null,
    val title: String?=null,
    val wm_size: String?=null,
    val wmplay: String?=null
)