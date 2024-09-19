package com.example.socialmediadownloader.data.repository

import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediadownloader.core.utils.Constants.toVideo
import com.example.socialmediadownloader.data.controller.NetworkClient
import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.data.response.RequestTypes
import com.example.socialmediadownloader.data.remote.tiktok.Tiktok
import com.example.socialmediadownloader.domain.repository.DownloadVideoRepository

class TiktokDownloader : DownloadVideoRepository {
    override suspend fun scrapeVideos(url: String): NetworkResponse<Video> {
        val videoUrl = "https://www.tikwm.com/api/?url=$url&hd=1"
        val response = NetworkClient.makeNetworkRequest<Tiktok>(
            url = videoUrl,
            requestType = RequestTypes.Get,
        )
        return when (response) {
            is NetworkResponse.Success -> {
                response.data?.let { tiktokData ->
                    val video = tiktokData.toVideo()
                    return video
                } ?: NetworkResponse.Failure("No video data found")
            }
            is NetworkResponse.Failure -> NetworkResponse.Failure(response.error)
            is NetworkResponse.Idle -> NetworkResponse.Idle()
            is NetworkResponse.Loading -> NetworkResponse.Loading()
        }
    }
}
