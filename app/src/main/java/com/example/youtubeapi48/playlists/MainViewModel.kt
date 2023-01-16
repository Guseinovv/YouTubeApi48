package com.example.youtubeapi48.playlists

import androidx.lifecycle.LiveData
import com.example.youtubeapi48.App
import com.example.youtubeapi48.core.ui.BaseViewModel
import com.example.youtubeapi48.data.model.Playlists

class MainViewModel : BaseViewModel() {


    fun playlist(): LiveData<Playlists> {
        return App().repository.getPlaylists()
    }
}