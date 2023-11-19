package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.team11.databinding.ActivityDiaryDetailBinding

class DiaryDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDiaryDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textTitle.text = intent.getStringExtra("title")
        binding.textOneIntro.text = intent.getStringExtra("oneIntro")
        binding.textContent.text = intent.getStringExtra("content")
// 이미지 추가
    }
}