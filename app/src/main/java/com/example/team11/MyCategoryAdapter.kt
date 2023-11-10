package com.example.team11

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.ItemGuideBinding

class CategoryViewHolder(val binding: ItemGuideBinding) : RecyclerView.ViewHolder(binding.root)

class MyCategoryAdapter(val context: Context, val itemList : MutableList<CategoryModel>): RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(ItemGuideBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val data = itemList[position]

        holder.binding.run{

            guideTitle.text = data.tvHeading
            guideImageView.setImageResource(data.titleImage)
        }

    }

}