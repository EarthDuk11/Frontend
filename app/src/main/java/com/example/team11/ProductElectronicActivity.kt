package com.example.team11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.team11.databinding.ActivityProductElectronicBinding

class ProductElectronicActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductElectronicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductElectronicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = mutableListOf<ItemProductModel>()
        val item1: ItemProductModel = ItemProductModel()
        item1.productId = "1"
        item1.titleImage = R.drawable.a_f
        item1.tvHeading = "마우스"
        itemList.add(item1)

        val item2: ItemProductModel = ItemProductModel()
        item2.productId = "2"
        item2.titleImage = R.drawable.a_b
        item2.tvHeading = "헤드셋"
        itemList.add(item2)

        val item3: ItemProductModel = ItemProductModel()
        item3.productId = "3"
        item3.titleImage = R.drawable.a_c
        item3.tvHeading = "프린터"
        itemList.add(item3)

        val item4: ItemProductModel = ItemProductModel()
        item4.productId = "4"
        item4.titleImage = R.drawable.a_d
        item4.tvHeading = "충전기"
        itemList.add(item4)

        val item5: ItemProductModel = ItemProductModel()
        item5.productId = "5"
        item5.titleImage = R.drawable.a_e
        item5.tvHeading = "노트북"
        itemList.add(item5)

        val item6: ItemProductModel = ItemProductModel()
        item6.productId = "6"
        item6.titleImage = R.drawable.a_a
        item6.tvHeading = "노트북"
        itemList.add(item6)

        val myProductAdapter = MyProductAdapter(this, itemList)
        binding.productRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.productRecyclerView.adapter = myProductAdapter

        val intent = Intent(this, GuideDetailActivity::class.java)

        myProductAdapter.setOnItemclickListner(object: MyProductAdapter.OnItemClickListner{
            override fun onItemClick(view: View, position: Int) {
                Log.d("MyProductAdapter", "아이템이 위치에서 클릭됨: $position")
                if(position == 0)   // 마우스 디테일로 이동함
                    Log.d("MyProductAdapter", "GuideDetailActivity 실행 중")
                    startActivity(intent)

            }
        })

    }
}