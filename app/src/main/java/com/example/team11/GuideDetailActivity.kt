package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.team11.databinding.ActivityGuideDetailBinding
import com.example.team11.databinding.ActivityMainBinding
import com.example.team11.databinding.ActivityProductElectronicBinding

class GuideDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityGuideDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val guideId = intent.getStringExtra("clicked_item_id")
        val productId = intent.getStringExtra("clicked_detail_item_id")

        if(guideId != null) {
            if (productId != null) {
                MyApplication.db
                    .collection("guides").document(guideId)
                    .collection("items").document(productId)
                    .collection("detail")
                    .get()
                    .addOnSuccessListener { documents->
                        for(document in documents){
                            val title = document.getString("detailTitle")
                            val contents = document.getString("detailContent")

                            binding.detailTitle.text=title
                            binding.detailContents.text=contents

                        }
                    }
                    .addOnFailureListener{ exception ->
                        Toast.makeText(this, "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
                    }
            }

        }

        binding.backToGuide.setOnClickListener {
            finish()
        }
    }
}