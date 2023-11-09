package com.example.team11

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.ItemBrandBinding
import com.example.team11.databinding.ItemProductBinding

class BrandViewHolder(val binding: ItemBrandBinding) : RecyclerView.ViewHolder(binding.root)

class MyBrandAdapter(val context: Context, val itemList:MutableList<ItemBrandModel>):RecyclerView.Adapter<BrandViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BrandViewHolder(ItemBrandBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val data = itemList[position]

        holder.binding.run{
            brandImageView.setImageResource(data.titleImage)
            brandName.text = data.tvHeading
            brandSub.text = data.tvsubscription
            brandVisit.text = data.tvVisit

        }

    }
}