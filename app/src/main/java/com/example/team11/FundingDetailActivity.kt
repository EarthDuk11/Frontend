package com.example.team11

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team11.MyApplication.Companion.auth
import com.example.team11.databinding.ActivityFundingDetailBinding
import com.example.team11.databinding.ActivityMainBinding
import com.google.firebase.firestore.Query
import java.util.*

class FundingDetailActivity : AppCompatActivity() {
    lateinit var docId : String
    lateinit var btnStar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityFundingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Intent로 전달된 데이터를 받아옴
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val title: String? = bundle.getString("title")
            val oneLine: String? = bundle.getString("oneLine")
            val content: String? = bundle.getString("content")
            val link: String? = bundle.getString("link")
            val date: String? = bundle.getString("date")
            //
            docId = bundle.getString("docId").toString()

            binding.fundingTitle.text = title
            binding.fundingOneLine.text = oneLine
            binding.fundingContent.text = content
            btnStar = binding.btnStar
            setLikeImage()

            binding.toFormLink.setOnClickListener {
                Toast.makeText(this, "펀딩 링크로 이동합니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                this.startActivity(intent)
            }

            binding.backToFundingBoard.setOnClickListener{
                finish()
            }
            binding.btnStar.setOnClickListener {
                //saveStoreFavorite()
                setLikeImage()
            }
        }
    }

    private fun setLikeImage() {
        val likedRef = MyApplication.db.collection("users").document(auth.uid.toString()).collection("favorite")
            .whereEqualTo("funding_id", docId)

        likedRef.get().addOnSuccessListener { querySnapshot ->
            val isLiked = !querySnapshot.isEmpty
           // val likeIconRes = if (isLiked) R.drawable.add_1 else R.drawable.add
            //binding.btnStar.setImageResource(likeIconRes)
            btnStar.text = "clicked"
        }
    }
//
//    private fun toggleLikeStatus() {
//        val likedRef = MyApplication.db.collection("users").document(auth.uid.toString()).collection("liked_movies")
//            .whereEqualTo("movieId", movieId)
//
//        likedRef.get().addOnSuccessListener { querySnapshot ->
//            if (querySnapshot.isEmpty) {
//                saveStore()
//            } else {
//                querySnapshot.documents.firstOrNull()?.reference?.delete()
//            }
//            setLikeImage()
//        }
//    }


    private fun saveStoreFavorite(){
        //add............................ uid와 id는 음... 어떻게 해야할지 모르겠음.
//        val content = binding.addContent.text.toString()
        val data = mapOf(
            "user_email" to MyApplication.email,
            "funding_id" to docId
            )

        MyApplication.db.collection("favorite")
            .add(data)// id 발급을 위해 먼저 저장
            .addOnSuccessListener {

                Log.d("favorite: ", "favorite Funding id : ${docId}, user_email : ${MyApplication.email}")

            }
            .addOnFailureListener{
                Log.d("favorite funding", "data save error", it)
            }
    }

}