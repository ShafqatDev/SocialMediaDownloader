package com.example.socialmediadownloader.data.repository

import android.util.Log
import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediadownloader.core.utils.Constants.toVideo
import com.example.socialmediadownloader.data.controller.NetworkClient
import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.data.remote.twitter.Twitter
import com.example.socialmediadownloader.data.response.RequestTypes
import com.example.socialmediadownloader.domain.repository.DownloadVideoRepository

class TwitterDownloader : DownloadVideoRepository {
    override suspend fun scrapeVideos(url: String): NetworkResponse<Video> {
        val id = url.substringAfterLast("/")
        Log.d("cvv", "fetchNewTwitterVideo: $id")
        val baseUrl = "https://tweeload.aculix.net/status/$id.json"
        Log.d("cvv", "fetchNewTwitterVideo: $baseUrl")
        val headers = mapOf(
            "Authorization" to "cKMQlY4jGCflOStlN3UfnWCxLQSb5GL7UPjPJ3jGS5fkno1Jaf"
        )
        val response = NetworkClient.makeNetworkRequest<Twitter>(
            url = baseUrl, requestType = RequestTypes.Get, headers = headers
        )

        return when (response) {
            is NetworkResponse.Success -> {
                response.data?.let { twitterData ->
                    val video = twitterData.toVideo()
                    return video
                } ?: NetworkResponse.Failure("No video data found")
            }

            is NetworkResponse.Failure -> NetworkResponse.Failure(response.error)
            is NetworkResponse.Idle -> NetworkResponse.Idle()
            is NetworkResponse.Loading -> NetworkResponse.Loading()
        }
    }
}