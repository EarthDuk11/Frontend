package com.example.team11


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
import com.example.team11.CategoryModel

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

        binding.guideRecyclerView.setOnClickListener {
            var bundle: Bundle = Bundle()
            bundle.putString("fromFrag", "가이드 카테고리 페이지로 이동")


        }

        Log.d("Category", "onStart")
        MyApplication.db.collection("guides")

            .orderBy("id", Query.Direction.DESCENDING)

            .get()
            .addOnSuccessListener { result ->
                val itemList = mutableListOf<CategoryModel>()
                for (document in result) {
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
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }

        return binding.root
    }


    override fun onStart() {
        super.onStart()

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