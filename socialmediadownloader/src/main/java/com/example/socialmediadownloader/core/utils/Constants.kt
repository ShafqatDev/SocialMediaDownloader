package com.example.socialmediadownloader.core.utils

import com.example.socialmediadownloader.domain.model.Quality
import com.example.socialmediadownloader.domain.model.SocialMediaType
import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediadownloader.data.remote.facebook.Facebook
import com.example.socialmediadownloader.data.remote.instagram.InstagramData
import com.example.socialmediadownloader.data.remote.linkedin.LinkedIn
import com.example.socialmediadownloader.data.remote.social.SocialVideo
import com.example.socialmediadownloader.data.remote.tiktok.Tiktok
import com.example.socialmediadownloader.data.remote.twitter.Twitter

object Constants {
    fun InstagramData.toVideo(): Video {
        val qualities = this.url?.map { mediaUrl ->
            Quality(
                qualityName = mediaUrl.type ?: "Unknown",
                qualityUrl = mediaUrl.url ?: "",
                qualitySize = mediaUrl.ext ?: ""
            )
        } ?: emptyList()

        return Video(
            title = this.meta?.title ?: "No title",
            socialMediaType = SocialMediaType.INSTAGRAM,
            quality = qualities,
        )
    }

    fun Tiktok.toVideo(): Video {
        val qualities = mutableListOf<Quality>()
        this.data?.let { data ->
            data.play?.let {
                qualities.add(
                    Quality(
                        qualityName = "SD",
                        qualityUrl = it,
                        qualitySize = data.size?.toString() ?: "Unknown"
                    )
                )
            }
            data.hdplay?.let {
                qualities.add(
                    Quality(
                        qualityName = "HD",
                        qualityUrl = it,
                        qualitySize = data.hd_size?.toString() ?: "Unknown"
                    )
                )
            }
            data.wmplay?.let {
                qualities.add(
                    Quality(
                        qualityName = "Watermarked",
                        qualityUrl = it,
                        qualitySize = data.wm_size ?: "Unknown"
                    )
                )
            }
        }

        return Video(
            title = this.data?.title ?: "No title",
            socialMediaType = SocialMediaType.TIKTOK,
            quality = qualities,
        )
    }

    fun Twitter.toVideo(): Video {
        val tweetVideo = this.tweet?.media?.videos?.firstOrNull()
        return tweetVideo?.let { video ->
            Video(title = this.tweet?.text ?: "No title",
                socialMediaType = SocialMediaType.TWITTER,
                quality = video.video_urls?.map { videoUrl ->
                    Quality(
                        qualityName = "Quality-${videoUrl.bitrate ?: "Unknown"}",
                        qualityUrl = videoUrl.url ?: "",
                        qualitySize = videoUrl.bitrate?.toString() ?: "Unknown"
                    )
                } ?: emptyList())
        } ?: Video(
            title = this.tweet?.text ?: "No title",
            socialMediaType = SocialMediaType.TWITTER,
            quality = emptyList(),
        )
    }

    fun List<LinkedIn>.toVideo(): Video {
        val videoTitle = this.firstOrNull { it.title != null }?.title ?: "Untitled Video"
        val videoQuality = this.filter { it.link != null && it.text != "Thumbnail" }.map {
            Quality(
                qualityName = "Hd Quality", qualityUrl = it.link ?: "", qualitySize = ""
            )
        }

        return Video(
            title = videoTitle,
            socialMediaType = SocialMediaType.LINKEDIN,
            quality = videoQuality,
        )
    }

    fun Facebook.toVideo(): Video {
        val videoTitle = this.title ?: "Untitled Video"
        val thumbnailUrl = this.thumbnail ?: ""
        val videoQuality = this.medias?.map { media ->
            Quality(
                qualityName = media.quality ?: "Unknown Quality",
                qualityUrl = media.url ?: "",
                qualitySize = media.formattedSize ?: ""
            )
        } ?: emptyList()

        return Video(
            title = videoTitle,
            socialMediaType = SocialMediaType.FACEBOOK,
            quality = videoQuality,
        )
    }
    fun SocialVideo.toVideo(): Video {
        val qualities = this.medias?.map { media ->
            Quality(
                qualityName = media.quality ?: "",
                qualityUrl = media.url ?: "",
                qualitySize = media.formattedSize ?: ""
            )
        } ?: emptyList()

        return Video(
            title = this.title ?: "",
            socialMediaType = SocialMediaType.SOCIALVIDEO,
            quality = qualities
        )
    }


}
