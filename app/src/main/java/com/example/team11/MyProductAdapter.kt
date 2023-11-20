package com.example.team11

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.team11.databinding.ItemProductBinding

class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

class MyProductAdapter (private val context: Context,
private val itemList: MutableList<ItemProductModel>,
private var itemClickListener: MyProductAdapter.OnItemClickListener // 클릭 리스너 추가
) : RecyclerView.Adapter<MyProductAdapter.ProductViewHolder>() {

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

            productCardView.setOnClickListener {
                // 클릭된 아이템의 ID를 전달
                data.productId?.let { it1 -> itemClickListener.onItemClick(it1) }
            }
        }
    }

    // 클릭 리스너 인터페이스 정의
    interface OnItemClickListener {
        fun onItemClick(itemId: String)
    }

    // 클릭 리스너 설정하는 메서드
    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)
}
