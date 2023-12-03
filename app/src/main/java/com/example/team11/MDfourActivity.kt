package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.team11.databinding.ActivityMdeightBinding
import com.example.team11.databinding.ActivityMdfourBinding

class MDfourActivity : AppCompatActivity() {
    lateinit var binding: ActivityMdfourBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMdfourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로 가기 버튼 클릭 시 액티비티 종료
        binding.chevronArr.setOnClickListener {
            onBackPressed()
        }
    }
}