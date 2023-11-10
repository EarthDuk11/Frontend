package com.example.team11

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.team11.databinding.FragmentProductsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding : FragmentProductsBinding

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
        binding = FragmentProductsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val itemList = mutableListOf<ItemProductModel>()
        val item1 : ItemProductModel = ItemProductModel()
        item1.productId = "1"
        item1.titleImage = R.drawable.a_a
        item1.tvHeading = "노트북"
        if (item1 != null) {
            itemList.add(item1)
            Log.d("fundingBoard", "item1 저장 완료")
        }

        val item2 : ItemProductModel = ItemProductModel()
        item2.productId = "2"
        item2.titleImage = R.drawable.a_b
        item2.tvHeading = "헤드셋"
        if (item2 != null) {
            itemList.add(item2)
            Log.d("fundingBoard", "item2 저장 완료")
        }

        val item3 : ItemProductModel = ItemProductModel()
        item3.productId = "3"
        item3.titleImage = R.drawable.a_c
        item3.tvHeading = "프린터"
        if (item3 != null) {
            itemList.add(item3)
            Log.d("fundingBoard", "item3 저장 완료")
        }

        val item4 : ItemProductModel = ItemProductModel()
        item4.productId = "4"
        item4.titleImage = R.drawable.a_d
        item4.tvHeading = "충전기"
        if (item4 != null) {
            itemList.add(item4)
            Log.d("fundingBoard", "item4 저장 완료")
        }

        val item5 : ItemProductModel = ItemProductModel()
        item5.productId = "5"
        item5.titleImage = R.drawable.a_e
        item5.tvHeading = "스피커"
        if (item5 != null) {
            itemList.add(item5)
            Log.d("fundingBoard", "item5 저장 완료")
        }

        val item6 : ItemProductModel = ItemProductModel()
        item6.productId = "6"
        item6.titleImage = R.drawable.a_f
        item6.tvHeading = "마우스"
        if (item6 != null) {
            itemList.add(item6)
            Log.d("fundingBoard", "item6 저장 완료")
        }



        binding.productRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.productRecyclerView.adapter = MyProductAdapter(requireContext(), itemList)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}