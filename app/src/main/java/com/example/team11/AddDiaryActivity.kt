package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.team11.databinding.ActivityAddDiaryBinding
import com.example.team11.databinding.ActivityFundingWriteBinding
import java.text.SimpleDateFormat
import java.util.*

class AddDiaryActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddDiaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_diary)

        binding= ActivityAddDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSave.setOnClickListener{

            if(binding.addTitle.text.isNotEmpty() && binding.addOneLine.text.isNotEmpty() &&
                binding.addContent.text.isNotEmpty()){ //
                //store 에 먼저 데이터를 저장 후 document id 값으로 업로드 파일 이름 지정
                saveStore()
            }else {
                Toast.makeText(this, "데이터가 모두 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    private fun saveStore(){
        //add............................ uid와 id는 음... 어떻게 해야할지 모르겠음.
        val data = mapOf(
            "content" to binding.addContent.text.toString(),
            "date" to dateToString(Date()),
            "email" to MyApplication.email,
            "hash" to false,
//            "id"  docid......?
            "img" to binding.addImage.text.toString(),
            "smileCount" to 0,
            "surprisedCount" to 0,
            "thumbsUpCount" to 0,
            "oneIntro" to binding.addOneLine.text.toString(),
            "title" to binding.addTitle.text.toString(),
            "uid" to MyApplication.auth.uid,
//            "img" to binding.addImage
        )

        MyApplication.db.collection("diaries")
            .add(data)// id 발급을 위해 먼저 저장
            .addOnSuccessListener {
                Log.d("Diary Write", "data save ok")

            }
            .addOnFailureListener{
                Log.d("Diary Write", "data save error", it)
            }
    }
    fun dateToString(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd hh:mm")
        // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREAN);
        //  tz = TimeZone.getTimeZone("Asia/Seoul");  // TimeZone에 표준시 설정
        //  dateFormat.setTimeZone(tz);                    //DateFormat에 TimeZone 설정

        return format.format(date)
    }
}