package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.team11.databinding.ActivityGuideDetailBinding
import com.example.team11.databinding.ActivityMainBinding
import com.example.team11.databinding.ActivityProductElectronicBinding

class GuideDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityGuideDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backToGuide.setOnClickListener {
            finish()
        }
    }
}