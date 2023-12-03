package com.example.team11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.team11.databinding.ActivityProductElectronicBinding
import com.google.firebase.firestore.FirebaseFirestore

class ProductElectronicActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var binding: ActivityProductElectronicBinding
    lateinit var adapter: MyProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductElectronicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //db

        val bundle: Bundle? = intent.extras
        if(bundle != null){
            val guideId = bundle.getString("id")
            val guideTitle = bundle.getString("title")
            val categoryDocId = bundle.getString("docId")


            binding.categoryTitle.text=guideTitle
            Log.d("중분류 ID", guideId.toString())

            if(guideId != null){
                MyApplication.db
                    .collection("guides").document(categoryDocId.toString())
                    .collection("items")
                    .get()
                    .addOnSuccessListener { items ->
                        val itemList = mutableListOf<ItemProductModel>()
                        for (document in items){
                            val item = document.toObject(ItemProductModel::class.java)
                            item.productId = document.id
                            itemList.add(item)
                        }

                        binding.productRecyclerView.layoutManager = GridLayoutManager(this, 2)
                        binding.productRecyclerView.adapter = MyProductAdapter(this, itemList)

                    }
                    .addOnFailureListener{ exception ->
                        Toast.makeText(this, "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
                    }

            }

        }

        // SearchView 내용 추가
        // MyProductAdapter를 초기화할 때 itemListFull도 초기화합니다.
//        val itemList = mutableListOf<ItemProductModel>()
//        adapter = MyProductAdapter(this, itemList)
//        adapter.itemListFull = itemList
//        binding.productRecyclerView.layoutManager = GridLayoutManager(this, 2)
//        binding.productRecyclerView.adapter = adapter

        // SearchView에 리스너 등록
        binding.searchView.setOnQueryTextListener(this)

    }

    // SearchView.OnQueryTextListener 구현
    override fun onQueryTextSubmit(query: String?): Boolean {
        // 검색 버튼을 눌렀을 때의 동작 (필요에 따라 구현)
        Log.d("검색 버튼을 눌렀을 때", "검색버튼이 눌렸습니다.")
        Log.d("검색어", "${query}")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // 검색어가 변경될 때마다 호출되는 동작
        Log.d("검색어가 변경될 때", "검색어가 변경되었습니다.")
        Log.d("변경된 검색어:", "${newText}")
        if (::adapter.isInitialized) { // Check if adapter is initialized before using it
            adapter.filter.filter(newText)
        } else if (binding.productRecyclerView.adapter is MyProductAdapter) {
            // 어댑터가 초기화되지 않았지만 RecyclerView에 MyProductAdapter 유형의 어댑터가 있는 경우 참조를 가져와 데이터를 필터링합니다.
            adapter = binding.productRecyclerView.adapter as MyProductAdapter
            adapter.filter.filter(newText)
        }

        return true
    }


}