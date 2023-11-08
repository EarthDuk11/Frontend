package com.example.team11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.team11.databinding.ActivityStreetGuideBinding

class StreetGuideActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityStreetGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}