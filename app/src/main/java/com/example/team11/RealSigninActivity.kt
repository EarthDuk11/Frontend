package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.team11.databinding.ActivityLoginBinding
import com.example.team11.databinding.ActivityRealSigninBinding

class RealSigninActivity : AppCompatActivity() {
    lateinit var binding: ActivityRealSigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRealSigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.singInBtn.setOnClickListener {
            // 이메일, 비밀번호 회원가입
            val email:String = binding.idText.text.toString()
            Log.d("회원가입 이메일: ", email)
            val password:String = binding.passwordText.text.toString()
            Log.d("회원가입 password: ", password)
            MyApplication.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        MyApplication.auth.currentUser?.sendEmailVerification()?.addOnCompleteListener{
                                sendTask ->
                            if(sendTask.isSuccessful){
                                Toast.makeText(baseContext, "이메일 확인을 통해 2차 인증해주세요.", Toast.LENGTH_LONG).show()
                                finish() // Login 화면으로 이동
                            }
                            else{ // 이메일 잘못 입력 ...
                                Toast.makeText(baseContext, "유효하지 않은 메일입니다.", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                    else{
                        Toast.makeText(baseContext, "회원가입 실패", Toast.LENGTH_LONG).show()
                    }
                    binding.idText.text.clear()
                    binding.passwordText.text.clear()
                }
        }
    }
}