package com.example.socialmediadownloader.data.remote.twitter

import kotlinx.serialization.Serializable

@Serializable
data class TwitterTweet(
    val twitterAuthor: TwitterAuthor?=null,
    val created_at: String?=null,
    val created_timestamp: Int?=null,
    val id: String?=null,
    val lang: String?=null,
    val likes: Int?=null,
    val twitterMedia: TwitterMedia?=null,
    val possibly_sensitive: Boolean?=null,
    val replies: Int?=null,
    val retweets: Int?=null,
    val text: String?=null,
    val twitter_card: String?=null,
    val url: String?=null
)