package com.example.team11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registerButton: TextView = findViewById(R.id.registerButton)

        // 회원가입 버튼 클릭 시 RealSigninActivity로 이동
        registerButton.setOnClickListener {
            val intent = Intent(this, RealSigninActivity::class.java)
            startActivity(intent)
        }
    }
}