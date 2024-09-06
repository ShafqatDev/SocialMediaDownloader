package com.example.socialmediadownloader.data.repository

import com.example.socialmediadownloader.core.utils.Constants.toVideo
import com.example.socialmediadownloader.data.controller.NetworkClient
import com.example.socialmediadownloader.data.remote.social.SocialVideo
import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediadownloader.domain.repository.DownloadVideoRepository

class SocialVideoDownloader : DownloadVideoRepository {
    override suspend fun scrapeVideos(url: String): NetworkResponse<Video> {
        val baseUrl = "https://getindevice.com/wp-json/aio-dl/video-data/"
        val headers = mapOf(
            "content-type" to "application/x-www-form-urlencoded; charset=utf-8",
            "host" to "getindevice.com"
        )
        val formData = mapOf(
            "url" to url
        )
        val response = NetworkClient.makeNetworkRequestXXForm<SocialVideo>(
            url = baseUrl, headers = headers, formData = formData
        )
        return when (response) {
            is NetworkResponse.Failure -> {
                NetworkResponse.Failure(response.error)
            }

            is NetworkResponse.Idle -> {
                NetworkResponse.Idle()
            }

            is NetworkResponse.Loading -> {
                NetworkResponse.Loading()
            }

            is NetworkResponse.Success -> {
                val video = response.data!!.toVideo()
                NetworkResponse.Success(video)
            }
        }
    }
}