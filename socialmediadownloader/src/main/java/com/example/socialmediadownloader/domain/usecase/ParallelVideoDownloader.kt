package com.example.socialmediadownloader.domain.usecase

import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.domain.model.Video
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class ParallelVideoDownloader {

    private val allVideoDownloader by lazy {
        AllVideoDownloaderUseCase()
    }
    private val specificVideoDownloader by lazy {
        SpecificVideoDownloaderUseCase()
    }

    suspend operator fun invoke(url: String): NetworkResponse<Video> {
        return suspendCancellableCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val job1 = async { allVideoDownloader.downloadVideo(url) }
                    val job2 = async { specificVideoDownloader.downloadVideo(url) }
                    val response1 = job1.await()
                    val response2 = job2.await()
                    when {
                        response1 is NetworkResponse.Success -> {
                            continuation.resume(NetworkResponse.Success(response1.data!!))
                        }
                        response2 is NetworkResponse.Success -> {
                            continuation.resume(NetworkResponse.Success(response2.data!!))
                        }
                    }
                } catch (e: Exception) {
                    continuation.resume(NetworkResponse.Failure("Error occurred: ${e.message}"))
                }
            }
        }
    }
}
