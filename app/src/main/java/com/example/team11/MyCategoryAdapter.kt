package com.example.team11

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.ItemGuideBinding
import java.util.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MyCategoryAdapter(
    private val context: Context,
    private val itemList: MutableList<CategoryModel>,
    private var itemClickListener: OnItemClickListener // 클릭 리스너 추가
) : RecyclerView.Adapter<MyCategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemGuideBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val data = itemList[position]

        holder.binding.run {
            guideTitle.text = data.title
            val titleImage = data.image

            Glide.with(context)
                .load(titleImage)
                .apply(
                    RequestOptions().placeholder(R.drawable.a)
                        .error(R.drawable.brand2)
                )
                .into(guideImageView)

            guideCardView.setOnClickListener {
                // 클릭된 아이템의 ID를 전달
                data.id?.let { it1 -> itemClickListener.onItemClick(it1) }
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

    class CategoryViewHolder(val binding: ItemGuideBinding) : RecyclerView.ViewHolder(binding.root)
}
