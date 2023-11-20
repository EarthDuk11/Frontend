package com.example.team11

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.ItemFundingBinding
import com.bumptech.glide.Glide

class MyFundingViewHolder(val binding: ItemFundingBinding) : RecyclerView.ViewHolder(binding.root)
class MyFundingAdapter (val context: Context, val itemList: MutableList<ItemFundingModel>): RecyclerView.Adapter<MyFundingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFundingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyFundingViewHolder(ItemFundingBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyFundingViewHolder, position: Int) {
        val data = itemList[position]

        holder.binding.run{
            fundingTitleView.text=data.title
            fundingOneLineView.text=data.oneIntro
        }

        //스토리지 이미지 다운로드........................ 이미지를 넣어줘야 함.
//        val imageRef = MyApplication.storage.reference.child("images/${data.docId}.jpg")  // 사용자의 userId를 넣어야 함.
//        imageRef.downloadUrl.addOnCompleteListener{task ->
//            if(task.isSuccessful){
//                // 다운로드 이미지를 ImageView에 보여줌.
//
//                GlideApp.with(context)
//                    .load(task.result)
//                    .into(holder.binding.fundingImageView)
//
//            }
//        }

        holder.binding.fundingRecyclerView.setOnClickListener{
            Intent(context, FundingDetailActivity::class.java).apply {
                var bundle : Bundle = Bundle()
                bundle.putString("title", data.title)
                bundle.putString("oneLine", data.oneIntro)
                bundle.putString("content", data.content)
                bundle.putString("link", data.link)
                bundle.putString("date", data.date)
                putExtras(bundle)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { context.startActivity(this) }

        }

    }
}