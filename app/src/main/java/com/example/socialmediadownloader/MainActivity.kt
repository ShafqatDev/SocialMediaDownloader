package com.example.socialmediadownloader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.domain.model.Video
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VideoDownloaderScreen()
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VideoDownloaderScreen() {
    val easyDownloader = EasyDownloader()
    var url by remember { mutableStateOf("https://x.com/CollinRugg/status/1832058727224287425") }
    var video by remember { mutableStateOf<NetworkResponse<Video>>(NetworkResponse.Idle()) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("Video URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                scope.launch {
                    video = NetworkResponse.Loading()
                    video = easyDownloader.downloadVideo(url)
                }
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Download")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (video) {
            is NetworkResponse.Idle -> Text("Enter URL")
            is NetworkResponse.Loading -> CircularProgressIndicator()
            is NetworkResponse.Failure -> Text(text = video.error ?: "Error")
            is NetworkResponse.Success -> video.data?.let { videoData ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = videoData.title)
                    GlideImage(
                        model = videoData.quality.firstOrNull()?.qualityUrl?.takeIf { it.isNotEmpty() },
                        contentDescription = "Video Thumbnail"
                    )
                    Button(onClick = {}) {
                        Text(text = "Download Quality: ${videoData.quality.firstOrNull()?.qualityName.takeIf { it?.isNotEmpty() == true } ?: "Quality"}")
                    }
                }
            }
        }
    }
}
