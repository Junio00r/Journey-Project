package com.devmobile.android.journey.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePaddingRelative
import com.devmobile.android.journey.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        // Need enable edge-to-edge to setWindowInsetsAnimationCallback works
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setWindowInsetsAnimationCallback(
            binding.root, object : WindowInsetsAnimationCompat.Callback(
                DISPATCH_MODE_CONTINUE_ON_SUBTREE
            ) {
                override fun onProgress(
                    insets: WindowInsetsCompat,
                    runningAnimations: MutableList<WindowInsetsAnimationCompat>
                ): WindowInsetsCompat {

                    val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
                    binding.root.updatePaddingRelative(bottom = ime.bottom)

                    return insets
                }
            }
        )
    }
}
