package com.mascir.nodesmanagerproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SplashActivity : AppCompatActivity() {
    private var counter = 0
    private var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        hideSystemUI()

        val img = findViewById<View>(R.id.img) as ImageView
        progressBar = findViewById(R.id.progress)
        progressBar!!.setVisibility(View.VISIBLE)

        val myanim = AnimationUtils.loadAnimation(this, R.anim.transition)
        img.startAnimation(myanim)
        progressBar!!.startAnimation(myanim)

        //function to load mainActivity

        //function to load mainActivity
        prog()
    }

    private fun hideSystemUI() {
        val actionBar = supportActionBar
        actionBar?.hide()
        val decorView = window.decorView
        decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_IMMERSIVE // Set the content to appear under the system bars so that the
                    // content doesn't resize when the system bars hide and show.
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    fun prog() {
        val t = Timer()
        val i = Intent(this, LoginActivity::class.java)
        val tt: TimerTask = object : TimerTask() {
            override fun run() {
                counter++
                progressBar!!.progress = counter
                if (counter == 80) {
                    startActivity(i)
                    finish()
                }
            } // }
        }
        t.schedule(tt, 0, 50)
    }
}