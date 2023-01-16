package com.example.youtubeapi48.playlists

import android.content.Intent
import android.net.ConnectivityManager
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi48.core.network.Connection
import com.example.youtubeapi48.core.ui.BaseActivity
import com.example.youtubeapi48.data.model.Item
import com.example.youtubeapi48.databinding.ActivityPlaylistsBinding

class PlaylistActivity : BaseActivity<MainViewModel, ActivityPlaylistsBinding>() {

    private lateinit var adapter: PlaylistAdapter

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    private fun clickListener(id: String) {
        Intent(this, InfoPlaylistActivity::class.java).apply {
            putExtra(KEY_FOR_ID, id)
            startActivity(this)
        }
    }

    override fun initViewModel() {
        viewModel.playlist().observe(this) {
            if (it != null) {
                adapter = PlaylistAdapter(it.items as ArrayList<Item>, this::clickListener)
            }
            binding.ryPlaylists.adapter = adapter
        }
    }

    override fun checkInternet() {
        Connection((getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager))
            .observe(this) {
                binding.includedInternet.isVisible = !it
                binding.ryPlaylists.isVisible = it
                if (it == true) {
                    initViewModel()
                }
            }
    }

    companion object {
        val KEY_FOR_ID = "HELLO"
    }
}