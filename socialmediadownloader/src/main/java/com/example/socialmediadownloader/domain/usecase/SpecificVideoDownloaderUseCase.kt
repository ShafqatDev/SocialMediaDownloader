package com.example.socialmediadownloader.domain.usecase

import com.example.socialmediadownloader.data.repository.FacebookDownloader
import com.example.socialmediadownloader.data.repository.InstagramDownloader
import com.example.socialmediadownloader.data.repository.LinkedInDownloader
import com.example.socialmediadownloader.data.repository.TiktokDownloader
import com.example.socialmediadownloader.data.repository.TwitterDownloader
import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediadownloader.domain.repository.DownloadVideoRepository

class SpecificVideoDownloaderUseCase(
) {
    suspend fun downloadVideo(url: String): NetworkResponse<Video> {
        val specificDownloader = getDownloaderByUrl(url)
        return specificDownloader?.scrapeVideos(url) ?: NetworkResponse.Failure("")
    }
    private fun getDownloaderByUrl(url: String): DownloadVideoRepository? {
        val facebookUrl = url.contains("facebook") || url.contains("fb.watch")
        val tiktokUrl =
            url.contains("tiktok") || url.contains("vm.tiktok") || url.contains("vt.tiktok")
        return if (tiktokUrl) {
            TiktokDownloader()
        } else if (url.contains("instagram")) {
            InstagramDownloader()
        } else if (url.contains("twitter")) {
            TwitterDownloader()
        } else if (url.contains("linkedin")) {
            LinkedInDownloader()
        } else if (facebookUrl) {
            FacebookDownloader()
        } else {
            return null
        }
    }
}