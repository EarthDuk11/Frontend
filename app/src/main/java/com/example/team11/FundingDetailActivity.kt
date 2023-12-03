package com.example.team11

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.team11.databinding.ActivityFundingDetailBinding

import java.util.*

class FundingDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityFundingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent로 전달된 데이터를 받아옴
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val docId: String? = bundle.getString("docId")
            val title: String? = bundle.getString("title")
            val oneLine: String? = bundle.getString("oneLine")
            val content: String? = bundle.getString("content")
            val link: String? = bundle.getString("link")
            val date: String? = bundle.getString("date")
            var favorite: String? = bundle.getString("favorite")

            binding.fundingTitle.text = title
            binding.fundingOneLine.text = oneLine
            binding.fundingContent.text = content

            if(favorite.equals("true")){
                binding.star.setImageResource(R.drawable.star_filled)
            }

            binding.toFormLink.setOnClickListener {
                Toast.makeText(this, "펀딩 링크로 이동합니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                this.startActivity(intent)
            }

            binding.backToFundingBoard.setOnClickListener{
                finish()
            }

            binding.star.setOnClickListener {

                val data = mapOf(
                    "funding_id" to docId,
                    "user_email" to MyApplication.email,
                )

                val fundingIdToDelete = docId
                val userEmailToDelete = MyApplication.email

                if(!favorite.equals("true")){
                    MyApplication.db.collection("favorite")
                        .add(data)
                        .addOnSuccessListener {
                            Log.d("Funding 즐겨찾기 추가 완료", "data save ok")
                            favorite="true"

                            binding.star.setImageResource(R.drawable.star_filled)
                            Toast.makeText(this, "즐겨찾기에 추가되었습니다.", Toast.LENGTH_SHORT).show()

                        }
                        .addOnFailureListener{
                            Log.d("Funding 즐겨찾기 추가 실패", "data save error", it)
                        }

                }else if(favorite.equals("true")){
                    MyApplication.db.collection("favorite")
                        .whereEqualTo("funding_id", fundingIdToDelete)
                        .whereEqualTo("user_email", userEmailToDelete)
                        .get()
                        .addOnSuccessListener { querySnapshot ->
                            for (document in querySnapshot) {
                                // 찾은 문서를 삭제
                                document.reference.delete()
                                    .addOnSuccessListener {
                                        Log.d("삭제 성공", "favorite 테이블에서 지워졌습니다.")
                                        // 삭제 성공 시에 UI 업데이트 등 추가 작업을 수행할 수 있습니다.
                                        Toast.makeText(this, "즐겨찾기에 제외되었습니다.", Toast.LENGTH_SHORT).show()
                                        binding.star.setImageResource(R.drawable.ph_star)
                                        favorite = "false"
                                    }
                                    .addOnFailureListener { e ->
                                        // 삭제 실패 시 실행되는 코드
                                        Log.w("삭제 실패", "Error deleting document", e)
                                    }
                            }
                        }
                        .addOnFailureListener { e ->
                            // 쿼리 실행 실패 시 실행되는 코드
                            Log.w("쿼리 실패", "Error getting documents.", e)
                        }
                }

            }
        }
    }

}