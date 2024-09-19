package com.example.socialmediadownloader

object EasyConfigs {
    private var parallelDownloadingAllowed = true

    fun enableParallelDownloading(check: Boolean = true) {
        parallelDownloadingAllowed = check
    }

    fun isParallelDownloadingAllowed(): Boolean {
        return parallelDownloadingAllowed
    }
}
