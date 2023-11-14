package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.team11.databinding.ActivityFundingWriteBinding
import com.example.team11.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class FundingWriteActivity : AppCompatActivity(){
    lateinit var binding: ActivityFundingWriteBinding
    lateinit var formLink: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityFundingWriteBinding.inflate(layoutInflater)
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
                formLink = editText.text.toString()
                Log.d("fundingFormLink", "폼 링크 첨부 완료")
                dialog.dismiss() // 다이얼로그 닫기
            }
        }

        binding.btnSave.setOnClickListener{

            if(binding.addTitle.text.isNotEmpty() && binding.addOneLine.text.isNotEmpty() &&
                    binding.addContent.text.isNotEmpty()){
                //store 에 먼저 데이터를 저장 후 document id 값으로 업로드 파일 이름 지정
                saveStore()
            }else {
                Toast.makeText(this, "데이터가 모두 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    fun dateToString(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd hh:mm")
        // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREAN);
        //  tz = TimeZone.getTimeZone("Asia/Seoul");  // TimeZone에 표준시 설정
        //  dateFormat.setTimeZone(tz);                    //DateFormat에 TimeZone 설정

        return format.format(date)
    }

    private fun saveStore(){
        //add............................ uid와 id는 음... 어떻게 해야할지 모르겠음.
        val data = mapOf(
            "content" to binding.addContent.text.toString(),
            "date" to dateToString(Date()),
            "email" to MyApplication.email,
            "link" to formLink,
            "oneIntro" to binding.addOneLine.text.toString(),
            "title" to binding.addTitle.text.toString()
        )

        MyApplication.db.collection("fundings")
            .add(data)// id 발급을 위해 먼저 저장
            .addOnSuccessListener {
                Log.d("Funding Write", "data save ok")

            }
            .addOnFailureListener{
                Log.d("Funding Write", "data save error", it)
            }
    }

}