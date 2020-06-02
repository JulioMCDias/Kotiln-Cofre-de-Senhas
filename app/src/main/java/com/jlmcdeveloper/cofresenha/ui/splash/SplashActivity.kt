package com.jlmcdeveloper.cofresenha.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.jlmcdeveloper.cofresenha.R
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel  by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        animation()
    }

    private fun animation(){
        image_cofre.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate))
        text_repository.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
        button_local.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
        button_driver.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
    }
}
