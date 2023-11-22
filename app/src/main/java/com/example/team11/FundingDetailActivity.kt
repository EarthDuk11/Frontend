package com.example.team11

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.team11.databinding.ActivityFundingDetailBinding
import com.example.team11.databinding.ActivityMainBinding

class FundingDetailActivity : AppCompatActivity() {
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

            binding.fundingTitle.text = title
            binding.fundingOneLine.text = oneLine
            binding.fundingContent.text = content

            binding.toFormLink.setOnClickListener {
                Toast.makeText(this, "펀딩 링크로 이동합니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                this.startActivity(intent)
            }

            binding.backToFundingBoard.setOnClickListener{
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("fragmentToFundingBoard", "FundingBoardFragment")
                }
                this.startActivity(intent)
            }
        }
    }

}