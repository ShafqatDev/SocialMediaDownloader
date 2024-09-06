package com.example.socialmediadownloader.data.repository

import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediadownloader.core.utils.Constants.toVideo
import com.example.socialmediadownloader.data.controller.NetworkClient
import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.data.remote.linkedin.LinkedIn
import com.example.socialmediadownloader.data.remote.linkedin.LinkedInModel
import com.example.socialmediadownloader.data.response.RequestTypes
import com.example.socialmediadownloader.domain.repository.DownloadVideoRepository

class LinkedInDownloader : DownloadVideoRepository {
    override suspend fun scrapeVideos(url: String): NetworkResponse<Video> {
        val baseUrl = "https://sdownloader.liforte.com/getvl2023"
        val headers = mapOf(
            "Content-Type" to "application/json; charset=utf-8",
            "Host" to "sdownloader.liforte.com",
            "lfcode" to "b060bf1a70504f6fc2a8f468e202908c",
            "pid" to "x2downloader.tubedownloader.downloaderforall.socialvideodownloader",
            "adeviceid" to "2066CB36385816AF12486BD7B7CE979E"
        )
        val response = NetworkClient.makeNetworkRequest<List<LinkedIn>>(
            url = baseUrl, headers = headers, requestType = RequestTypes.Post(
                body = LinkedInModel(
                    Url = url,
                    avc = 17
                )
            )
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
                val linkedInData = response.data ?: emptyList()
                val videoData = linkedInData.toVideo()
                NetworkResponse.Success(videoData)
            }
        }
    }
}