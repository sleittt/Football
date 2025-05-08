package com.example.football

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.os.VibratorManager
import android.os.VibrationEffect
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var scoreL=0
    var scoreR=0

    lateinit var ScoreL: TextView
    lateinit var btnClickL: ImageView
    lateinit var ScoreR: TextView
    lateinit var btnClickR: ImageView

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            scoreL = savedInstanceState.getInt("SAVED_SCOREL")
            scoreR = savedInstanceState.getInt("SAVED_SCORER")
        }
        ScoreL = findViewById(R.id.ScoreL)
        btnClickL = findViewById(R.id.btnClickL)
        ScoreR = findViewById(R.id.ScoreR)
        btnClickR = findViewById(R.id.btnClickR)

        updateScore()

        btnClickL.setOnClickListener {
            it.startAnimation(loadAnimation(this, R.anim.click))
            vibrateDevice()
            scoreL++
            updateScore()
        }
        btnClickR.setOnClickListener {
            it.startAnimation(loadAnimation(this, R.anim.click))
            vibrateDevice()
            scoreR++
            updateScore()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun vibrateDevice() {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
        vibrator.vibrate(VibrationEffect.createOneShot(100, 150))
    }

    private fun updateScore() {
        ScoreL.text = scoreL.toString()
        ScoreR.text = scoreR.toString()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SAVED_SCOREL", scoreL)
        outState.putInt("SAVED_SCORER", scoreR)
    }
}