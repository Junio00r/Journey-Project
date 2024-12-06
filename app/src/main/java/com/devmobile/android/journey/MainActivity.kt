package com.devmobile.android.journey

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.devmobile.android.journey.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}