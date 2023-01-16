package com.example.youtubeapi48

import android.app.Application
import com.example.youtubeapi48.repository.Repository

class App : Application() {

    val repository: Repository by lazy {
        Repository()
    }
}