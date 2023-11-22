package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.team11.databinding.ActivityDiaryDetailBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DiaryDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDiaryDetailBinding
    lateinit var docId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textTitle.text = intent.getStringExtra("title")
        binding.textOneIntro.text = intent.getStringExtra("oneIntro")
        binding.textContent.text = intent.getStringExtra("content")
        // img.............................................
        docId = intent.getStringExtra("docId").toString()
        val imgRef = MyApplication.storage.reference.child("images/${docId}.jpg")
        imgRef.downloadUrl.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Glide.with(baseContext)
                    .load(task.result)
                    .into(binding.img)
            }
        }
        setBtnEvent()


    }
    private fun setBtnEvent(){
        binding.button1.setOnClickListener {
            // SMILE_BUTTON에 해당하는 데이터베이스 필드에 1을 추가
            updateReactionCount(SMILE_BUTTON, 1)
        }

        binding.button2.setOnClickListener {
            // THUMBSUP_BUTTON에 해당하는 데이터베이스 필드에 1을 추가
            updateReactionCount(THUMBSUP_BUTTON, 1)
        }

        binding.button3.setOnClickListener {
            // SURPRISED_BUTTON에 해당하는 데이터베이스 필드에 1을 추가
            updateReactionCount(SURPRISED_BUTTON, 1)
        }

    }

        private fun updateReactionCount(reactionType: Int, countToAdd: Int) {
            val db = Firebase.firestore
            val docRef = db.collection("diaries").document(docId)
            setBtnReactionVisibilityToGone()

            docRef.get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null) {
                            // 현재 데이터베이스 필드의 값을 가져오기
                            var currentCount = document.getLong(getFieldName(reactionType)) ?: 0
                            // 값을 증가시키기
                            currentCount += countToAdd

                            // 데이터베이스 업데이트
                            val data = hashMapOf(
                                getFieldName(reactionType) to currentCount
                                // 다른 필드도 필요한 경우 추가
                            )

                            docRef.update(data as Map<String, Any>)
                                .addOnSuccessListener {
                                    // 업데이트 성공 처리
                                    setResult(RESULT_OK)
//                                    finish()
                                }
                                .addOnFailureListener { exception ->
                                    // 업데이트 실패 처리
                                }
                        } else {
                            // 문서가 존재하지 않음
                        }
                    } else {
                        // 작업이 실패한 경우 처리
                    }
                }
        }

        private fun getFieldName(reactionType: Int): String {
            return when (reactionType) {
                SMILE_BUTTON -> "smileCount"
                THUMBSUP_BUTTON -> "thumbsUpCount"
                SURPRISED_BUTTON -> "surprisedCount"
                else -> ""
            }
        }


    private fun setBtnReactionVisibilityToGone(){
        binding.button1.visibility = View.GONE
        binding.button2.visibility = View.GONE
        binding.button3.visibility = View.GONE
    }

    companion object{
        const val  SMILE_BUTTON  = 1
        const val THUMBSUP_BUTTON = 2
        const val SURPRISED_BUTTON = 3

    }
}