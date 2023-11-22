package com.example.team11

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.team11.databinding.FragmentGuideBinding
import com.google.firebase.firestore.Query

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GuideFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GuideFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentGuideBinding
/*
    private lateinit var adapter: CategoryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoriesitem : ArrayList<CategoryModel>

    lateinit var imageId : ArrayList<Int>
    lateinit var heading : ArrayList<String>
    lateinit var categories : ArrayList<String>*/

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
        //return inflater.inflate(R.layout.fragment_guide, container, false)
        binding = FragmentGuideBinding.inflate(inflater, container, false)

        binding.guideRecyclerView.setOnClickListener{
            var bundle : Bundle = Bundle()
            bundle.putString("fromFrag", "가이드 카테고리 페이지로 이동")


        }

        Log.d("Category", "onStart")
        MyApplication.db.collection("guides")

            .orderBy("id", Query.Direction.DESCENDING)

            .get()
            .addOnSuccessListener { result ->
                val itemList = mutableListOf<CategoryModel>()
                for(document in result){
                    val item = document.toObject(CategoryModel::class.java)
                    item.docId = document.id
                    itemList.add(item)
                }

//                val adapter = MyCategoryAdapter(requireContext(), itemList, object: MyCategoryAdapter.OnItemClickListener{
//                    override fun onItemClick(itemId:String){
//                        val intent = Intent(requireContext(), ProductElectronicActivity::class.java)
//                        intent.putExtra("clicked_item_id", itemId) // 여기서 putExtra 사용
//                        requireContext().startActivity(intent)
//                    }
//                })
                binding.guideRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.guideRecyclerView.adapter = MyCategoryAdapter(requireContext(), itemList)

            }
            .addOnFailureListener{ exception ->
                Toast.makeText(requireContext(), "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }

        return binding.root
    }


    override fun onStart() {
        super.onStart()
        /*
        val itemList = mutableListOf<CategoryModel>()
        val item1 : CategoryModel = CategoryModel()
        item1.guideId ="1"
        item1.titleImage = R.drawable.a
        item1.tvHeading = "가전제품"
        if (item1 != null) {
            itemList.add(item1)
            Log.d("fundingBoard", "item1 저장 완료")
        }

        val item2 : CategoryModel = CategoryModel()
        item1.guideId ="2"
        item2.titleImage = R.drawable.b
        item2.tvHeading = "식료품"
        if (item2 != null) {
            itemList.add(item2)
            Log.d("fundingBoard", "item2 저장 완료")
        }

        val item3 : CategoryModel = CategoryModel()
        item3.guideId ="3"
        item3.titleImage = R.drawable.c
        item3.tvHeading = "주방용품"
        if (item3 != null) {
            itemList.add(item3)
            Log.d("fundingBoard", "item3 저장 완료")
        }

        val item4 : CategoryModel = CategoryModel()
        item4.guideId ="3"
        item4.titleImage = R.drawable.d
        item4.tvHeading = "생활용품"
        if (item4 != null) {
            itemList.add(item4)
            Log.d("fundingBoard", "item4 저장 완료")
        }

        val myCategoryAdapter = MyCategoryAdapter(requireContext(), itemList)
        binding.guideRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.guideRecyclerView.adapter = myCategoryAdapter
        val intent = Intent(this.context,ProductElectronicActivity::class.java)

        myCategoryAdapter.setOnItemclickListner(object: MyCategoryAdapter.OnItemClickListner{
            override fun onItemClick(view: View, position: Int) {
                if(position == 0)   // 가전제품
                    startActivity(intent)
            }
        })
        */
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GuideFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GuideFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}