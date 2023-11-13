package com.example.team11

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.DiaryItemBinding

class MyFeedViewHolder(val binding: DiaryItemBinding) : RecyclerView.ViewHolder(binding.root)

class MyFeedAdapter(val context: Context, val itemList: MutableList<DiaryFeedModel>): RecyclerView.Adapter<MyFeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFeedViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return MyFeedViewHolder(DiaryItemBinding.inflate(layoutInflater))
    }

    override fun onBindViewHolder(holder: MyFeedViewHolder, position: Int) {
        val item = itemList[position] // 데이터 리스트의 이름이 itemList이므로 수정
         holder.binding.title.text = item.title // 예제 코드에서는 TextView를 사용하는 것으로 보이므로, 뷰 바인딩을 사용하지 않을 경우 주석 처리
         holder.binding.content.text = item.content // 예제 코드에서는 TextView를 사용하는 것으로 보이므로, 뷰 바인딩을 사용하지 않을 경우 주석 처리

        holder.binding.title.setOnClickListener {
            // 프래그먼트 이동

        }
    }








override fun getItemCount() = itemList.size // 데이터 리스트의 이름이 itemList이므로 수정
}
