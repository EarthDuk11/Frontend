package com.example.team11

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.DiaryItemBinding
import com.google.firebase.firestore.FirebaseFirestore

class MyFeedViewHolder(val binding: DiaryItemBinding) : RecyclerView.ViewHolder(binding.root)

class MyFeedAdapter(val context: Context, val itemList: MutableList<DiaryFeedModel>): RecyclerView.Adapter<MyFeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFeedViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return MyFeedViewHolder(DiaryItemBinding.inflate(layoutInflater))
    }

    override fun onBindViewHolder(holder: MyFeedViewHolder, position: Int) {
        val item = itemList[position] // 데이터 리스트의 이름이 itemList이므로 수정
         holder.binding.title.text = item.title // 예제 코드에서는 TextView를 사용하는 것으로 보이므로, 뷰 바인딩을 사용하지 않을 경우 주석 처리
         holder.binding.textOneIntro.text = item.oneIntro // 예제 코드에서는 TextView를 사용하는 것으로 보이므로, 뷰 바인딩을 사용하지 않을 경우 주석 처리


            // Firestore에서 user 모델 데이터 가져오기
            val db = FirebaseFirestore.getInstance()

        holder.binding.title.setOnClickListener {
            val bundle: Bundle = Bundle()
            bundle.putString("content", item.content)
            bundle.putString("date", item.date)
            bundle.putString("hash", item.hash.toString())
            bundle.putString("img", item.img)
            bundle.putString("oneIntro", item.oneIntro)
            bundle.putString("smileCount", item.smileCount.toString())
            bundle.putString("surprisedCount", item.surprisedCount.toString())
            bundle.putString("thumbsUpCount", item.thumbsUpCount.toString())
            bundle.putString("title", item.title)
            bundle.putString("uid", item.uid)

            Intent(context, DiaryDetailActivity::class.java).apply {
                putExtras(bundle)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { context.startActivity(this) }

        }
    }








override fun getItemCount() = itemList.size // 데이터 리스트의 이름이 itemList이므로 수정
}
