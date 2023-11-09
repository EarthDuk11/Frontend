package com.example.team11

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.FragmentPromotionBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PromotionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PromotionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentPromotionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPromotionBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onStart() {
        super.onStart()
        val itemList = mutableListOf<ItemBrandModel>()

        val item1:ItemBrandModel=ItemBrandModel()
        item1.productId ="1"
        //item1.titleImage
        item1.tvHeading ="Allbirds"
        item1.titleImage= R.drawable.brand1
        item1.tvsubscription = "브랜드 설명 왈라왈라브랜드 설명 왈라왈라브랜드 설명 왈라왈라브랜드 설명 왈라왈라브랜드 설명 왈라왈라"
        item1.tvVisit ="17명이 이 브랜드를 방문하였습니다."
        item1.siteLink = "링크"
        if (item1 != null) {
            itemList.add(item1)
            Log.d("fundingBoard", "item1 저장 완료")
        }

        val item2:ItemBrandModel=ItemBrandModel()
        item2.productId ="2"
        //item1.titleImage
        item2.tvHeading ="아모레"
        item2.titleImage= R.drawable.brand2
        item2.tvsubscription = "브랜드 설명 왈라왈라브랜드 설명 왈라왈라브랜드 설명 왈라왈라브랜드 설명 왈라왈라브랜드 설명 왈라왈라"
        item2.tvVisit ="12명이 이 브랜드를 방문하였습니다."
        item2.siteLink = "링크"
        if (item2 != null) {
            itemList.add(item2)
            Log.d("fundingBoard", "item2 저장 완료")
        }

        val item3:ItemBrandModel=ItemBrandModel()
        item3.productId ="3"
        //item1.titleImage
        item3.tvHeading ="브랜드 이름"
        item3.titleImage= R.drawable.brand3
        item3.tvsubscription = "브랜드 설명 왈라왈라브랜드 설명 왈라왈라브랜드 설명 왈라왈라브랜드 설명 왈라왈라브랜드 설명 왈라왈라"
        item3.tvVisit ="7명이 이 브랜드를 방문하였습니다."
        item3.siteLink = "링크"
        if (item3 != null) {
            itemList.add(item3)
            Log.d("fundingBoard", "item3 저장 완료")
        }

        val item4:ItemBrandModel=ItemBrandModel()
        item4.productId ="4"
        //item1.titleImage
        item4.tvHeading ="행사 이름"
        item4.titleImage= R.drawable.earth
        item4.tvsubscription = "브랜드 설명 왈라왈라브랜드 설명 왈라왈라브랜드 설명 왈라왈라브랜드 설명 왈라왈라브랜드 설명 왈라왈라"
        item4.tvVisit ="7명이 이 행사를 방문하였습니다."
        item4.siteLink = "링크"
        if (item4 != null) {
            itemList.add(item4)
            Log.d("fundingBoard", "item4 저장 완료")
        }




        binding.brandRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.brandRecycler.adapter = MyBrandAdapter(requireContext(), itemList)

    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PromotionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PromotionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}



