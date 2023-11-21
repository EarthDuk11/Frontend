package com.example.team11

import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.team11.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val registerButton: TextView = findViewById(R.id.registerButton)

        // 회원가입 버튼 클릭 시 RealSigninActivity로 이동
        registerButton.setOnClickListener {
            val intent = Intent(this, RealSigninActivity::class.java)
            startActivity(intent)
        }

//        binding.loginBtn.setOnClickListener {
//            // 이메일, 비밀번호 회원가입
//            val email:String = binding.idText.text.toString()
//            val password:String = binding.passwordText.text.toString()
//            MyApplication.auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this){ task ->
//                    if(task.isSuccessful){
//                        MyApplication.auth.currentUser?.sendEmailVerification()?.addOnCompleteListener{
//                                sendTask ->
//                            if(sendTask.isSuccessful){
//                                Toast.makeText(baseContext, "이메일 확인을 통해 2차 인증해주세요.", Toast.LENGTH_LONG).show()
//                                changeVisibility("logout")
//                            }
//                            else{ // 이메일 잘못 입력 ...
//                                Toast.makeText(baseContext, "메일 전송 실패...", Toast.LENGTH_LONG).show()
//                                changeVisibility("logout")
//                            }
//                        }
//                    }
//                    else{
//                        Toast.makeText(baseContext, "회원가입 실패..", Toast.LENGTH_LONG).show()
//                        changeVisibility("logout")
//                    }
//                    binding.idText.text.clear()
//                    binding.passwordText.text.clear()
//                }
//        }
    }
}