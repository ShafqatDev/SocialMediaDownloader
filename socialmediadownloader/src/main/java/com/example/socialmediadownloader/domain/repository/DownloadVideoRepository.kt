package com.example.socialmediadownloader.domain.repository

import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediadownloader.data.response.NetworkResponse

interface DownloadVideoRepository {
    suspend fun scrapeVideos(url: String): NetworkResponse<Video>
}