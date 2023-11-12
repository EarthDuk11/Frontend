package com.example.team11

import android.content.Context
import android.view.LayoutInflater
import android.view.View
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
        holder.binding.productCardView.setOnClickListener{
            val pos = position
            if(pos != RecyclerView.NO_POSITION && itemClickListner != null){
                itemClickListner.onItemClick(holder.binding.productCardView, pos)
            }
        }

    }
    //커스텀 리스너 인터페이스 정의
    interface OnItemClickListner{
        fun onItemClick(view: View, position: Int)
    }
    //리스너 인터페이스 객체 전달하는 메서드
    fun setOnItemclickListner(onItemClickListner: OnItemClickListner){
        itemClickListner = onItemClickListner
    }
    private lateinit var itemClickListner: OnItemClickListner
}