package com.example.socialmediadownloader.data.repository

import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediadownloader.core.utils.Constants.toVideo
import com.example.socialmediadownloader.data.controller.NetworkClient
import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.data.remote.instagram.InstagramData
import com.example.socialmediadownloader.domain.repository.DownloadVideoRepository

class InstagramDownloader : DownloadVideoRepository {
    override suspend fun scrapeVideos(url: String): NetworkResponse<Video> {
        val baseUrl = "https://www.save-free.com/process"
        val headers = mapOf(
            "Cookie" to "ga=GA1.1.354220085.1725261522; _gcl_au=1.1.1646344803.1725261523; HstCfa4752989=1725261523671; HstCla4752989=1725261523671; HstCmu4752989=1725261523671; HstPn4752989=1; HstPt4752989=1; HstCnv4752989=1; HstCns4752989=1; c_ref_4752989=https%3A%2F%2Fwww.google.com%2F; cf_clearance=8Q4Ij9Fzj2EH6Xp7KAsjQeVjBr92szm1VMg6j4cr11Y-1725261524-1.2.1.1-GZsBtXvCKtt_LLepg1xdJcDxGK7CfACxyFgrHQZ8GnynHrchx4Uc.MUgNLcz_fGV36u2Hex.cA1sDSIZyUA6AwhGF45H4bMH0ihcSCWcMvlC8O45AZgnd8mTeKMG3YIus2XgANs7tDYxCGAtvqy0lJgYdYnKF2h3Ed5N54i6XbPFzx8ieBDGOUoQJJipN4XQGL_xA6hkjw02UL.oTMJ854yWVHFARJYaxQS6aQAnNqeaIAGRvREE3shGmcXfnfS5DaIdE5q94e6jAYA_dmhBNfSa89nLSTTDlFTuxzvIwvAIQtajlQOXISMulFbmr72Xb.elpvylv4A_yDPGAafNQJgZ6u4ecytPwjaSSBB60Ve92CrJ5cMvg4kpjwwNq12Y; __gads=ID=449fc1262abae443:T=1725261523:RT=1725261523:S=ALNI_MaC7tl47UofUJ-witBrFe6HiCaCVg; __gpi=UID=00000ee6f66481ec:T=1725261523:RT=1725261523:S=ALNI_MaAU3_Fwur7WP46dyKnCVzOsk18Iw; __eoi=ID=d28d29870ed328c5:T=1725261523:RT=1725261523:S=AA-AfjbgqDfyfKcOhetlj_e3ipYj; _ga_9M9G1NYVWE=GS1.1.1725261522.1.0.1725261524.58.0.0; _ga_TCKL78VSRE=GS1.1.1725261523.1.0.1725261524.59.0.0; FCNEC=%5B%5B%22AKsRol9ZammSGA-pVgQ0lPv36Dj84qh5ZyN4dbxqmNlol3SRJRf85p7Sk9rtJGbdnwlNOODM4sGJUWvRludGvxOx4FET7ykDBdF-B_pb8AQC6Vk6phkMG9K2pbz_IgknhqnwOVKE4H9kwLDMnLUSd6GmOFwUFOE_rw%3D%3D%22%5D%5D",
            "Referer" to "https://www.save-free.com/",
            "Origin" to "https://www.save-free.com",
            "X-Valy-Cache" to "true"
        )
        val formData = mapOf(
            "instagram_url" to url, "type" to "media", "resource" to "save"
        )
        val response = NetworkClient.makeNetworkRequestXXForm<List<InstagramData>>(
            url = baseUrl, headers = headers, formData = formData
        )

        return when (response) {
            is NetworkResponse.Success -> {
                response.data?.let { instagramDataList ->
                    val video = instagramDataList.firstOrNull()?.toVideo()
                    return video!!
                } ?: NetworkResponse.Failure("Failed to extract data")
            }

            is NetworkResponse.Failure -> {
                NetworkResponse.Failure(response.error)
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