package com.example.team11

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.team11.databinding.ItemProductBinding

class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

class MyProductAdapter (val context: Context, val itemList: MutableList<ItemProductModel>
//private var itemClickListener: MyProductAdapter.OnItemClickListener // 클릭 리스너 추가
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = itemList[position]

        holder.binding.run {
            productTitle.text = data.productTitle
            /*Glide.with(context)
                .load(titleImage)
                .apply(
                    RequestOptions().placeholder(R.drawable.a)
                        .error(R.drawable.brand2)
                )
                .into(guideImageView)*/

//            productCardView.setOnClickListener {
//                // 클릭된 아이템의 ID를 전달
//                data.productId?.let { it1 -> itemClickListener.onItemClick(it1) }
//            }
        }

        val imageRef = MyApplication.storage.reference.child("categories/${data.productId}.png")
        Log.d("url 출력", imageRef.toString())
        imageRef.downloadUrl.addOnCompleteListener{task ->
            if(task.isSuccessful){
                // 다운로드 이미지를 ImageView에 보여줌.
                Glide.with(context)
                    .load(task.result)
                    .into(holder.binding.productImage)

            }
        }

        holder.binding.productCardView.setOnClickListener {
            Intent(context, GuideDetailActivity::class.java).apply {
                var bundle: Bundle = Bundle()
                bundle.putString("title", data.productTitle)
                bundle.putString("productId", data.productId)
                bundle.putString("content", data.content)
                putExtras(bundle)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { context.startActivity(this) }
        }

    }

    // 클릭 리스너 인터페이스 정의
//    interface OnItemClickListener {
//        fun onItemClick(itemId: String)
//    }

    // 클릭 리스너 설정하는 메서드
//    fun setOnItemClickListener(listener: OnItemClickListener) {
//        itemClickListener = listener
//    }

    }
