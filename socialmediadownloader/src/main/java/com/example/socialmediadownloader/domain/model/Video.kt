package com.example.socialmediadownloader.domain.model


data class Video(
    val title: String = "",
    val socialMediaType: SocialMediaType = SocialMediaType.INSTAGRAM,
    val quality: List<Quality> = emptyList(),
)
