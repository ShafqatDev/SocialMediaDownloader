package com.example.socialmediadownloader.data.repository

import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediadownloader.core.utils.Constants.toVideo
import com.example.socialmediadownloader.data.controller.NetworkClient
import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.data.remote.facebook.Facebook
import com.example.socialmediadownloader.domain.repository.DownloadVideoRepository

class FacebookDownloader : DownloadVideoRepository {
    override suspend fun scrapeVideos(url: String): NetworkResponse<Video> {
        val baseUrl = "https://fbdown.online/wp-json/aio-dl/video-data/"
        val formData = mapOf(
            "url" to url,
            "token" to "488a64de7738419358fdcd3b0e8723d38e52ba37fb4964b68ed96305690d8fc7"
        )

        val headers = mapOf(
            "Content-Type" to "application/x-www-form-urlencoded",
            "Cookie" to "pll_language=en; _ga_VGK87VS9QM=GS1.1.1725477301.1.0.1725477301.0.0.0; _ga=GA1.1.1971596075.1725477302; __gads=ID=d3a20b1d148fa214:T=1725477304:RT=1725477304:S=ALNI_MYLuY_6PMHlZgL-BajCZVzffh8Q4Q; __gpi=UID=00000ed690437c57:T=1725477304:RT=1725477304:S=ALNI_MYpX6M-gdMTX0EViWe3R35lL0l8Og; __eoi=ID=dc0513bc3361b65a:T=1725477304:RT=1725477304:S=AA-AfjbOcxcQjhAaTSTDeHz4LW6h; FCNEC=%5B%5B%22AKsRol9rjmEQ42LMKvwA5BMLZ6oCetyVwImscp2fjzsCd_Tv9tGCdBRuS__6htUNc7kZ6hdXwCXoClJkfnuwEQJXsfS-3MLA2qUqntsLk-qQP9NwLq46YzTTgC7JOjN25T4CmMxNR-V4EKH8jxA-P2HRRAdFRHSbSA%3D%3D%22%5D%5D; PHPSESSID=nuk475mbp54n130cvh0plbi7vs",
            "Referer" to "https://fbdown.online/"
        )
        val response = NetworkClient.makeNetworkRequestXXForm<Facebook>(
            url = baseUrl, formData = formData, headers = headers
        )
        return when (response) {
            is NetworkResponse.Success -> {
                response.data?.let { facebookData ->
                    val video = facebookData.toVideo()
                    NetworkResponse.Success(video)
                } ?: NetworkResponse.Failure(error = "Failed to extract data")
            }

            is NetworkResponse.Failure -> {
                NetworkResponse.Failure(error = response.error)
            }

            is NetworkResponse.Idle -> {
                NetworkResponse.Idle()
            }

            is NetworkResponse.Loading -> {
                NetworkResponse.Loading()
            }
        }
    }
}
