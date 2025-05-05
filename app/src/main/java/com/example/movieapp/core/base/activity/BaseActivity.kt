package com.example.movieapp.core.base.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<viewBinding : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initialize()
    }

    abstract fun getViewBinding(): viewBinding

    open fun initialize() {}
}
