package com.example.socialmediadownloader

import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediadownloader.domain.usecase.ParallelVideoDownloader
import com.example.socialmediadownloader.domain.usecase.SpecificVideoDownloaderUseCase

class EasyDownloader {

    private val parallelVideoDownloader by lazy {
        ParallelVideoDownloader()
    }
    private val specificVideoDownloader by lazy {
        SpecificVideoDownloaderUseCase()
    }

    suspend fun downloadVideo(url: String): NetworkResponse<Video> {
        return if (EasyConfigs.isParallelDownloadingAllowed()) {
            parallelVideoDownloader.invoke(url)
        } else {
            specificVideoDownloader.downloadVideo(url)
        }
    }

}