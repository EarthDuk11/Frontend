package com.example.team11

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.team11.databinding.ActivityDiaryDetailBinding
import com.google.firebase.database.collection.LLRBNode
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.properties.Delegates


class DiaryDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDiaryDetailBinding
    lateinit var docId : String
    lateinit var gridview : GridView
//    lateinit var adapter : DiaryContentAdapter
var smileBtnClicked by Delegates.notNull<Boolean>()
    var thumbsUpBtnClicked by Delegates.notNull<Boolean>()
    var surprisedBtnClicked by Delegates.notNull<Boolean>()

    lateinit var strList : CharArray



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDiaryDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.textTitle.text = intent.getStringExtra("title")
        binding.textOneIntro.text = intent.getStringExtra("oneIntro")
        // update.....................................................
        binding.textContent.text = intent.getStringExtra("content")
        // img.............................................
        docId = intent.getStringExtra("docId").toString()
        val imgRef = MyApplication.storage.reference.child("images/${docId}.jpg")
        imgRef.downloadUrl.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Glide.with(baseContext)
                    .load(task.result)
                    .into(binding.img)
            }
        }
        smileBtnClicked = false
        thumbsUpBtnClicked = false
        surprisedBtnClicked = false

        setBtnEvent()

        // 그림일기..............................................
//        gridview = binding.textContent
//        adapter = DiaryContentAdapter()
//        // adapter안에 정보 담기
//        var contentsIntypeString = intent.getStringExtra("content")
//        adapter.addItem(contentsIntypeString!!.toCharArray())
//        // 리스트뷰에 adapter설정
//
//        gridview = binding.textContent
//
//        binding.textContent.adapter = DiaryContentAdapter(requireContext(), itemList)


    }
//    class GridViewadpater : BaseAdapter() {
//        var items : CharArray = CharArray(50)
//        public fun addItem(item : CharArray) {
//            items = item
//        }
//        override fun getCount(): Int {
//            return items.size
//        }
//
//        override fun getItem(position: Int): Any {
//            return items.get(position);
//        }
//
//        override fun getItemId(position: Int): Long {
//            return position.toLong();
//        }
//
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//
//            var charItem = items.get(position)
//
//
////            if (convertView == null) {
//////                val inflater: LayoutInflater =
//////                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//////                convertView = inflater.inflate(R.layout.gre, viewGroup, false)
////                val tv_num: TextView = binding.te
////
////                tv_num.setText(charItem)
////
////                Log.d(TAG, "getView() - [ $position" + " ] " + bearItem.getName())
////            } else {
////                var view: View? = View(context)
////                view = convertView
////            }
//            return View(Context)
//           }
//        }

    private fun setBtnEvent() {

        binding.button1.setOnClickListener {
            if (!smileBtnClicked) {
                binding.button1.setImageResource(R.drawable.smile_clicked)
                smileBtnClicked = true
                updateReactionCount(SMILE_BUTTON, 1)
            } else {
                binding.button1.setImageResource(R.drawable.smile2)
                smileBtnClicked = false
                updateReactionCount(SMILE_BUTTON, -1)
            }
        }

        binding.button2.setOnClickListener {
            if (!thumbsUpBtnClicked) {
                binding.button2.setImageResource(R.drawable.thumbs_up_clicked)
                thumbsUpBtnClicked = true
                updateReactionCount(THUMBSUP_BUTTON, 1)
            } else {
                binding.button2.setImageResource(R.drawable.thumbsup)
                thumbsUpBtnClicked = false
                updateReactionCount(THUMBSUP_BUTTON, -1)
            }
        }

        binding.button3.setOnClickListener {
            if (!surprisedBtnClicked) {
                binding.button3.setImageResource(R.drawable.surprised_clicked)
                surprisedBtnClicked = true
                updateReactionCount(SURPRISED_BUTTON, 1)
            } else {
                binding.button3.setImageResource(R.drawable.surprised)
                surprisedBtnClicked = false
                updateReactionCount(SURPRISED_BUTTON, -1)
            }
        }
    }
    /*
    *
    *  var thumbsUpBtnClicked : Boolean = false
    var surprisedBtnClicked : Boolean = false*/

        private fun updateReactionCount(reactionType: Int, countToAdd: Int) {
            val db = Firebase.firestore
            val docRef = db.collection("diaries").document(docId)
            //setBtnReactionVisibilityToGone()

            docRef.get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null) {
                            // 현재 데이터베이스 필드의 값을 가져오기
                            var currentCount = document.getLong(getFieldName(reactionType)) ?: 0
                            // 값을 증가시키기
                            currentCount += countToAdd

                            setReactionBtnBackgroundColor(reactionType)
                            // 데이터베이스 업데이트
                            val data = hashMapOf(
                                getFieldName(reactionType) to currentCount
                                // 다른 필드도 필요한 경우 추가
                            )

                            docRef.update(data as Map<String, Any>)
                                .addOnSuccessListener {
                                    // 업데이트 성공 처리
                                    setResult(RESULT_OK)
//                                    finish()
                                }
                                .addOnFailureListener { exception ->
                                    // 업데이트 실패 처리
                                }
                        } else {
                            // 문서가 존재하지 않음
                        }
                    } else {
                        // 작업이 실패한 경우 처리
                    }
                }
        }

        private fun getFieldName(reactionType: Int): String {
            return when (reactionType) {
                SMILE_BUTTON -> "smileCount"
                THUMBSUP_BUTTON -> "thumbsUpCount"
                SURPRISED_BUTTON -> "surprisedCount"
                else -> ""
            }
        }
    @SuppressLint("ResourceAsColor")
    private fun setReactionBtnBackgroundColor(reactionType: Int)  {
        when(reactionType) {
            SMILE_BUTTON -> binding.button1.setBackgroundColor(R.drawable.thumbsup)
            THUMBSUP_BUTTON -> binding.button2.setBackgroundColor(R.color.second_color)
            SURPRISED_BUTTON -> binding.button3.setBackgroundColor(R.color.second_color)

        }

    }


    private fun setBtnReactionVisibilityToGone(){
        binding.button1.visibility = View.GONE
        binding.button2.visibility = View.GONE
        binding.button3.visibility = View.GONE
    }

    companion object{
        const val  SMILE_BUTTON  = 1
        const val THUMBSUP_BUTTON = 2
        const val SURPRISED_BUTTON = 3

    }
}