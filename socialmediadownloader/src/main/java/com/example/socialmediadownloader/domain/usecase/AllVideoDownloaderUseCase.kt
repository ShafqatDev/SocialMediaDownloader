package com.example.socialmediadownloader.domain.usecase

import com.example.socialmediadownloader.data.repository.SocialVideoDownloader
import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.domain.model.Video

class AllVideoDownloaderUseCase {
    private val allVideoDownloader = SocialVideoDownloader()
    suspend fun downloadVideo(url: String): NetworkResponse<Video> {
        return allVideoDownloader.scrapeVideos(url)
    }
}