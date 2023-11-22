//package com.example.team11
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.GridLayout
//import androidx.recyclerview.widget.RecyclerView
//import com.example.team11.databinding.DiaryItemBinding
//import com.example.team11.databinding.ItemDiaryContentBinding
//import com.google.firebase.firestore.FirebaseFirestore
//
//
//class DiaryFeedViewHolder(val binding: ItemDiaryContentBinding) : RecyclerView.ViewHolder(binding.root)
//
//class DiaryContentAdapter(val context: Context, val itemList: MutableList<DiaryContentModel>): GridLayout.Adapter<DiaryFeedViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryFeedViewHolder {
//
//        val layoutInflater = LayoutInflater.from(parent.context)
//        return DiaryFeedViewHolder(ItemDiaryContentBinding.inflate(layoutInflater))
//    }
//
//    override fun onBindViewHolder(holder: DiaryFeedViewHolder, position: Int) {
//        val item = itemList[position] // 데이터 리스트의 이름이 itemList이므로 수정
//
//        holder.binding.run {
//            holder.binding.charItem =
//
//        }
//
//        // Firestore에서 user 모델 데이터 가져오기
//        val db = FirebaseFirestore.getInstance()
//
//
//        holder.binding.title.setOnClickListener {
//            val bundle: Bundle = Bundle()
//            bundle.putString("content", item.content)
//            bundle.putString("date", item.date)
//            bundle.putString("hash", item.hash.toString())
//            bundle.putString("img", item.img)
//            bundle.putString("oneIntro", item.oneIntro)
//            item.smileCount?.let { it1 -> bundle.putInt("smileCount", it1) }
//            item.surprisedCount?.let { it1 -> bundle.putInt("surprisedCount", it1) }
//            item.thumbsUpCount?.let { it1 -> bundle.putInt("thumbsUpCount", it1) }
//            bundle.putString("title", item.title)
//            bundle.putString("uid", item.uid)
//
//            bundle.putString("docId", item.docId)
//
//            Intent(context, DiaryDetailActivity::class.java).apply {
//                putExtras(bundle)
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            }.run { context.startActivity(this) }
//
//        }
//    }
//
//    override fun getItemCount() = itemList.size // 데이터 리스트의 이름이 itemList이므로 수정
//}
