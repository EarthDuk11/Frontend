package com.example.team11

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.ItemGuideBinding
import com.example.team11.databinding.ItemProductBinding

class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

class MyProductAdapter(val context: Context, val itemList: MutableList<ItemProductModel>):RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(ItemProductBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = itemList[position]

        holder.binding.run{
            productTitle.text = data.tvHeading
            productImage.setImageResource(data.titleImage)
        }

    }
}