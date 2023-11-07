package dev.awd.auth

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import dev.awd.auth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun animateKeyboard() {
        val view = binding.navHostFragment
        ViewCompat.setWindowInsetsAnimationCallback(
            view,
            object : WindowInsetsAnimationCompat.Callback(
                DISPATCH_MODE_STOP
            ) {
                var startBottom = 0f
                var endBottom = 0f

                override fun onPrepare(
                    animation: WindowInsetsAnimationCompat
                ) {
                    startBottom = view.bottom.toFloat()
                    Log.d(TAG, "onPrepare Animation: $startBottom")
                }

                override fun onStart(
                    animation: WindowInsetsAnimationCompat,
                    bounds: WindowInsetsAnimationCompat.BoundsCompat
                ): WindowInsetsAnimationCompat.BoundsCompat {
                    // Record the position of the view after the IME transition.
                    endBottom = view.bottom.toFloat()
                    Log.d(TAG, "onStart Animation at :$endBottom")
                    return bounds
                }

                override fun onProgress(
                    insets: WindowInsetsCompat,
                    runningAnimations: MutableList<WindowInsetsAnimationCompat>
                ): WindowInsetsCompat {
                    val imeAnimation = runningAnimations.find {
                        Log.d(TAG, "onProgress Animation")
                        it.typeMask and WindowInsetsCompat.Type.ime() != 0
                    } ?: return insets

                    view.translationY =
                        (startBottom - endBottom) * (1 - imeAnimation.interpolatedFraction)

                    return insets
                }
            }

        )
    }

}