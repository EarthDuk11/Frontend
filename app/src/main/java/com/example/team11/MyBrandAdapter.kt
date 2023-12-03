package com.example.team11

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.ItemBrandBinding


class BrandViewHolder(val binding: ItemBrandBinding) : RecyclerView.ViewHolder(binding.root)




class MyBrandAdapter(val context: Context, val itemList: MutableList<ItemBrandModel>) :
    RecyclerView.Adapter<BrandViewHolder>() {

    private val sharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

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

        holder.binding.run {
            brandImageView.setImageResource(data.titleImage)
            brandName.text = data.tvHeading
            brandSub.text = data.tvsubscription
            brandVisit.text = concat(getCountVisit(data).toString(), "명이 방문했습니다.")

        }

        holder.binding.brandPageButton.setOnClickListener {
            Toast.makeText(context, "사이트로 이동합니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.siteLink))
            context.startActivity(intent)

            // 해당 아이템의 countVisit 값을 증가시키고 저장
            val existingCountVisit = getCountVisit(data)

            // Update the countVisit value
            data.countVisit = existingCountVisit + 1

            // Save the updated countVisit value in SharedPreferences
            sharedPreferences.edit().putInt("countVisit_${data.productId}", data.countVisit).apply()

            // Update the UI
            notifyItemChanged(position)
            holder.binding.brandVisit.text = concat(data.countVisit.toString(), "명이 방문했습니다.")



        }
    }

    private fun getCountVisit(item: ItemBrandModel): Int {
        // SharedPreferences에서 해당 아이템의 countVisit 값을 읽어옴
        return sharedPreferences.getInt("countVisit_${item.productId}", 0)
    }


    fun concat(s1: String, s2: String): String {
        return s1 + s2
    }
}
