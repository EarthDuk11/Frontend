package com.example.team11

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team11.databinding.ActivityFundingBoardBinding
import com.example.team11.databinding.ActivityMainBinding

class FundingBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityFundingBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val itemList = mutableListOf<ItemFundingModel>()   db에서 내용을 가져와야 함.
//        for(document in result)
//
//        binding.fundingBoardRecyclerView.layoutManager = LinearLayoutManager(this)
//        binding.fundingBoardRecyclerView.adapter = MyFundingAdapter(this, itemList)
//        binding.fundingBoardRecyclerView.addItemDecoration(DividerItemDecoration(this,
//        LinearLayoutManager.VERTICAL))

//        MyApplication.db.collection("news")
//
//            .orderBy("date", DownloadManager.Query.Direction.DESCENDING)
//
//            .get()
//            .addOnSuccessListener { result ->
//                val itemList = mutableListOf<ItemBoardModel>()
//                for(document in result){
//                    val item = document.toObject(ItemBoardModel::class.java)
//                    item.docId = document.id
//                    itemList.add(item)
//                }
//                binding.boardRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//                binding.boardRecyclerView.adapter = MyBoardAdapter(requireContext(), itemList)
//            }
//            .addOnFailureListener{ exception ->
//                Toast.makeText(requireContext(), "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
//            }
    }
}