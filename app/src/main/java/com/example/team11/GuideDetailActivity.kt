package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.team11.databinding.ActivityGuideDetailBinding
import com.example.team11.databinding.ActivityMainBinding
import com.example.team11.databinding.ActivityProductElectronicBinding

class GuideDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityGuideDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        if(bundle != null){
            val productId: String? = bundle.getString("productId")
            val productTitle: String? = bundle.getString("title")
            val productContent: String? = bundle.getString("content")

            binding.detailTitle.text = productTitle
            binding.detailContents.text = productContent

            val imageRef = MyApplication.storage.reference.child("categories/${productId}.png")
            Log.d("url 출력", imageRef.toString())
            imageRef.downloadUrl.addOnCompleteListener{task ->
                if(task.isSuccessful){
                    // 다운로드 이미지를 ImageView에 보여줌.
                    Glide.with(this)
                        .load(task.result)
                        .into(binding.detailImage)

                }
            }

        }


        binding.backToGuide.setOnClickListener {
            finish()
        }
    }
}