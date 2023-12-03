package com.example.team11

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.team11.databinding.ItemProductBinding
import java.util.*
import kotlin.collections.ArrayList

class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

class MyProductAdapter (val context: Context, val itemList: MutableList<ItemProductModel>
) : RecyclerView.Adapter<ProductViewHolder>(), Filterable { // searchView로 Filterable 추가

    var itemListFull: List<ItemProductModel> = ArrayList(itemList.toList())  // searchView로 변수 추가

    // 생성자에서 itemListFull 초기화 추가
    init {
        itemListFull = ArrayList(itemList)
    }

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

    override fun getFilter(): Filter {  // searchView로 Filter 추가
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                Log.d("MyProductAdapter", "Perform Filtering - Constraint: $constraint")
                Log.d("MyProductAdapter", "Perform Filtering - Full List: $itemListFull")
                Log.d("ItemListFull에는 뭐가 들었을까?", "${itemListFull}")
                val filteredList = mutableListOf<ItemProductModel>()

                if (constraint.isNullOrEmpty()) {
                    Log.d("MyProductAdapter", "Perform Filtering - No constraint, Full list used")
                    filteredList.addAll(itemListFull)
                } else {
                    val filterPattern = constraint.toString().trim()
                    Log.d("Filtered Pattern", filterPattern)

                    Log.d("itemList엔 뭐가 들었을까?", "${itemList}")
                    for (item in itemListFull) {
                        Log.d("아이템의 이름", "${item.productTitle}")
                        val productTitle = item.productTitle
                        if (productTitle?.contains(filterPattern) == true) {
                            filteredList.add(item)
                        }
                    }
                    Log.d("MyProductAdapter", "Perform Filtering - Filtered list: $filteredList")
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                Log.d("MyProductAdapter", "Publish Results - Constraint: $constraint")
                Log.d("MyProductAdapter", "Publish Results - Filtered : ${results?.values}")
                itemList.clear()
                itemList.addAll(results?.values as List<ItemProductModel>)
                notifyDataSetChanged()
            }
        }
    }


}