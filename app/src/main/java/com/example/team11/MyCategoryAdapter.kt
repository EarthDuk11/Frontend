package com.example.team11

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.ItemGuideBinding
import java.util.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.team11.databinding.ItemFundingBinding

class MyCategoryViewHolder(val binding: ItemGuideBinding) : RecyclerView.ViewHolder(binding.root)
class MyCategoryAdapter(val context: Context, val itemList: MutableList<CategoryModel>
    //private var itemClickListener: OnItemClickListener // 클릭 리스너 추가
) : RecyclerView.Adapter<MyCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyCategoryViewHolder(ItemGuideBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: MyCategoryViewHolder, position: Int) {
        val data = itemList[position]

        holder.binding.run {
            guideTitle.text = data.title
            /*
            val titleImage = data.image

            Glide.with(context)
                .load(titleImage)
                .apply(
                    RequestOptions().placeholder()
                        .error(R.drawable.brand2)
                )
                .into(guideImageView)*/


//            guideCardView.setOnClickListener {
//                // 클릭된 아이템의 ID를 전달
//                data.id?.let { it1 -> itemClickListener.onItemClick(it1) }
//            }
        }

        val imageRef = MyApplication.storage.reference.child("categories/${data.docId}.png")
        Log.d("url 출력", imageRef.toString())
        imageRef.downloadUrl.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Glide.with(context)
                    .load(task.result)
                    .into(holder.binding.guideImageView)
            }
        }

        holder.binding.guideCardView.setOnClickListener {
            // 어디로 이동해야 하지 -> ProductElectronicActivity로 이동
            Intent(context, ProductElectronicActivity::class.java).apply {
                var bundle : Bundle = Bundle()
                bundle.putString("docId", data.docId)
                bundle.putString("id", data.id)
                bundle.putString("title", data.title)
                putExtras(bundle)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { context.startActivity(this) }
        }

        // 클릭 리스너 인터페이스 정의
//    interface OnItemClickListener {
//        fun onItemClick(itemId: String)
//    }

        // 클릭 리스너 설정하는 메서드
//    fun setOnItemClickListener(listener: OnItemClickListener) {
//        itemClickListener = listener
//    }

        // class CategoryViewHolder(val binding: ItemGuideBinding) : RecyclerView.ViewHolder(binding.root)
    }
}
