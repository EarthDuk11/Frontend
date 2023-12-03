package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.team11.databinding.ActivityMapDetailBinding

class MapDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMapDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로 가기 버튼 클릭 시 액티비티 종료
        binding.chevronArr.setOnClickListener {
            onBackPressed()
        }
    }


}