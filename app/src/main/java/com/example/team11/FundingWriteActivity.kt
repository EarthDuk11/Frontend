package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.team11.databinding.ActivityFundingWriteBinding
import com.example.team11.databinding.ActivityMainBinding

class FundingWriteActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding= ActivityFundingWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val formLinkButton = binding.btnInsertLink
        formLinkButton.setOnClickListener {
            // 다이얼로그 빌더 생성
            val dialogBuilder = AlertDialog.Builder(this)
            // 다이얼로그 레이아웃 설정
            val dialogView = layoutInflater.inflate(R.layout.popup_funding_link_layout, null)
            dialogBuilder.setView(dialogView)

            val editText = dialogView.findViewById<EditText>(R.id.formLink)
            val submitButton = dialogView.findViewById<Button>(R.id.submitButton)

            // 다이얼로그 표시
            val dialog = dialogBuilder.create()
            dialog.show()

            // 확인 버튼 클릭 이벤트 처리
            submitButton.setOnClickListener {
                val userInput = editText.text.toString()
                Log.d("fundingFormLink", "폼 링크 첨부 완료")
                dialog.dismiss() // 다이얼로그 닫기
            }
        }

    }

}