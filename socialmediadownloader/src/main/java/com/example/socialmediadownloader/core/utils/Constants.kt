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
import com.example.socialmediadownloader.data.response.NetworkResponse

object Constants {
    fun InstagramData.toVideo(): NetworkResponse<Video> {
        val qualities = this.url?.map { mediaUrl ->
            Quality(
                qualityName = mediaUrl.type ?: "Unknown",
                qualityUrl = mediaUrl.url ?: "",
                qualitySize = mediaUrl.ext ?: ""
            )
        } ?: emptyList()

        return if (qualities.isNotEmpty()) {
            NetworkResponse.Success(
                Video(
                    title = this.meta?.title ?: "No title",
                    socialMediaType = SocialMediaType.INSTAGRAM,
                    quality = qualities,
                    thumbnail = thumb ?: this.url?.first()?.url
                )
            )
        } else {
            NetworkResponse.Failure("No video qualities found")
        }
    }

    fun Tiktok.toVideo(): NetworkResponse<Video> {
        val qualities = mutableListOf<Quality>()
        this.tiktokData?.let { data ->
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

        return if (qualities.isNotEmpty()) {
            NetworkResponse.Success(
                Video(
                    title = this.tiktokData?.title ?: "No title",
                    socialMediaType = SocialMediaType.TIKTOK,
                    quality = qualities,
                    thumbnail = tiktokData?.origin_cover ?: tiktokData?.play
                )
            )
        } else {
            NetworkResponse.Failure("No video qualities found")
        }
    }

    fun Twitter.toVideo(): NetworkResponse<Video> {
        val tweetVideo = this.twitterTweet?.twitterMedia?.twitterVideos?.firstOrNull()
        val qualities = tweetVideo?.video_urls?.map { videoUrl ->
            Quality(
                qualityName = "Quality-${videoUrl.bitrate ?: "Unknown"}",
                qualityUrl = videoUrl.url ?: "",
                qualitySize = videoUrl.bitrate?.toString() ?: "Unknown"
            )
        } ?: emptyList()

        return if (qualities.isNotEmpty()) {
            NetworkResponse.Success(
                Video(
                    title = this.twitterTweet?.text ?: "No title",
                    socialMediaType = SocialMediaType.TWITTER,
                    quality = qualities,
                    thumbnail = this.twitterTweet?.twitterMedia?.twitterVideos?.first()?.thumbnail_url
                        ?: tweetVideo?.video_urls?.first()?.url
                )
            )
        } else {
            NetworkResponse.Failure("No video qualities found")
        }
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
            thumbnail = this.first().link
        )
    }

    fun Facebook.toVideo(): Video {
        val videoTitle = this.title ?: "Untitled Video"
        val thumbnailUrl = this.thumbnail ?: ""
        val videoQuality = this.facebookMedia?.map { media ->
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
            thumbnail = thumbnailUrl ?: this.facebookMedia?.first()?.url
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
            quality = qualities,
            thumbnail = this.thumbnail ?: ""
        )
    }
}
