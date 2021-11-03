package com.serranoie.crypteck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.transition.doOnEnd
import com.serranoie.crypteck.databinding.ActivitySplashBinding
import com.serranoie.crypteck.utils.Constants

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startAnimation()
        }, Constants.SPLASH_ANIMATION_DELAY)
    }

    private fun startAnimation() {
        val transition = ChangeBounds().apply {
            interpolator = OvershootInterpolator()
            duration = Constants.SPLASH_ANIMATION_DURATION

            doOnEnd {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            }
        }

        TransitionManager.beginDelayedTransition(binding.root, transition)
        val finishingConstraintSet = ConstraintSet()

        finishingConstraintSet.clone(this, R.layout.activity_splash2)
        finishingConstraintSet.applyTo(binding.root)
    }
}