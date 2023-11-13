package com.example.team11

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.ItemFundingBinding


class MyFundingViewHolder(val binding: ItemFundingBinding) : RecyclerView.ViewHolder(binding.root)
class MyFundingAdapter (val context: Context, val itemList: MutableList<ItemFundingModel>): RecyclerView.Adapter<MyFundingViewHolder>(){
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
//        val imageRef = MyApplication.storage.reference.child("images/${data.docId}.jpg")
//        imageRef.downloadUrl.addOnCompleteListener{task ->
//            if(task.isSuccessful){
//                // 다운로드 이미지를 ImageView에 보여줌.
//
//                GlideApp.with(context)
//                    .load(task.result)
//                    .into(holder.binding.itemImageView)
//
//            }
//        }

    }
}