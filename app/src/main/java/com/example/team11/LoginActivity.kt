package com.example.team11

import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.team11.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

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

        binding.loginBtn.setOnClickListener {
            //이메일, 비밀번호 로그인.......................
            val email:String = binding.idText.text.toString()
            val password:String = binding.passwordText.text.toString()
            if (email==null || password==null){
                Toast.makeText(baseContext, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show()
            }
            MyApplication.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        if(MyApplication.checkAuth()){
                            MyApplication.email = email
                            // 로그인 후 MainFragment로 이동 가능
                            val intent = Intent(this, MainActivity::class.java).apply {
                                putExtra("loginSuccess", "LoginActivity")
                            }
                            this.startActivity(intent)
                        }
                        else{
                            Toast.makeText(baseContext, "이메일 인증 실패", Toast.LENGTH_LONG).show()
                        }
                    }
                    else {
                        Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_LONG).show()
                    }
                    binding.idText.text.clear()
                    binding.passwordText.text.clear()
                }

        }

        val requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            // ApiException : Google Play 서비스 호출이 실패했을 때 태스크에서 반환할 예외
            try{
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                MyApplication.auth.signInWithCredential(credential)
                    .addOnCompleteListener(this){task ->
                        if(task.isSuccessful){
                            MyApplication.email = account.email
                            Log.d("mobileApp", "GoogleSignIn - Successful")
                            // 구글 인증 후 MainFragment로 이동 가능
                            val intent = Intent(this, MainActivity::class.java).apply {
                                putExtra("googleSuccess", "LoginActivity")
                            }
                            this.startActivity(intent)
                        }else{
                            Log.d("mobileApp", "GoogleSignIn - NOT Successful")
                        }
                    }// 인증이 완료되었을 때의 처리
            }catch(e: ApiException){
                Log.d("mobileApp", "GoogleSignIn - ${e.message}")
            }
        }
        binding.googleBtn.setOnClickListener {
            //구글 로그인....................
            val gso : GoogleSignInOptions = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val signInIntent : Intent = GoogleSignIn.getClient(this, gso).signInIntent
            requestLauncher.launch(signInIntent)
        }

    }
}