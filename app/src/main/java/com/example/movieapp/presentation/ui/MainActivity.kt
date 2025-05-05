package com.example.movieapp.presentation.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import com.example.movieapp.core.base.activity.BaseActivity
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.presentation.adapter.ViewpagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var viewpagerAdapter: ViewpagerAdapter

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        super.initialize()
        setUpViewPager()
        createNotificationChannel()
    }

    private fun setUpViewPager() {
        val listOfFragments = listOf(MovieFragment(), FavoriteMoviesFragment())

        viewpagerAdapter = ViewpagerAdapter(
            listOfFragments,
            supportFragmentManager,
            lifecycle
        )

        binding.fragmentContainer.adapter = viewpagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.fragmentContainer) { tab, position ->
            tab.text = when (position) {
                0 -> "Home"
                1 -> "Favourite"
                else -> ""
            }
        }.attach()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "download_channel",
            "Download Notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}

