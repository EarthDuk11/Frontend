package com.example.team11

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team11.databinding.FragmentMyJoinFundingBinding
import com.example.team11.databinding.FragmentMyOpenFundingBinding
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.Query

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyJoinFundingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyJoinFundingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentMyJoinFundingBinding

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
        binding = FragmentMyJoinFundingBinding.inflate(inflater, container, false)

        MyApplication.db.collection("favorite")
            .whereEqualTo("user_email", MyApplication.email)
            .get()
            .addOnSuccessListener { result ->
                val fundingIds = result.documents.map { it.getString("funding_id") }
                // 사용자가 즐겨찾기를 누른 funding 게시글의 funding_id에 해당하는 게시글을 들고 옴.
                MyApplication.db.collection("fundings")
                    .whereIn(FieldPath.documentId(), fundingIds)
                   //.orderBy("date", Query.Direction.DESCENDING)
                    .get()
                    .addOnSuccessListener { funding_result ->
                        val itemList = mutableListOf<ItemFundingModel>()
                        for(document in funding_result){
                            val item = document.toObject(ItemFundingModel::class.java)
                            item.docId = document.id
                            if(fundingIds.contains(item.docId)){
                                item.isFavorite = "true"
                            }
                            itemList.add(item)
                        }
                        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        binding.recyclerView.adapter = MyFundingAdapter(requireContext(), itemList)
                    }
                    .addOnFailureListener{ exception ->
                        Toast.makeText(requireContext(), "funding_ids 찾기 실패: ${exception.message}", Toast.LENGTH_SHORT).show()
                        Log.d("id 찾기 실패", ": ${exception.message}")
                    }
            }
            .addOnFailureListener{ exception ->
                Toast.makeText(requireContext(), "서버 데이터 획득 실패: ${exception.message}", Toast.LENGTH_SHORT).show()
            }



        return binding.root
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun refreshData() {
        MyApplication.db.collection("favorite")
            .whereEqualTo("user_email", MyApplication.email)
            .get()
            .addOnSuccessListener { result ->
                val fundingIds = result.documents.map { it.getString("funding_id") }
                MyApplication.db.collection("fundings")
                    .whereIn(FieldPath.documentId(), fundingIds)
                    .get()
                    .addOnSuccessListener { funding_result ->
                        val itemList = mutableListOf<ItemFundingModel>()
                        for(document in funding_result){
                            val item = document.toObject(ItemFundingModel::class.java)
                            item.docId = document.id
                            if(fundingIds.contains(item.docId)){
                                item.isFavorite = "true"
                            }
                            itemList.add(item)
                        }
                        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        binding.recyclerView.adapter = MyFundingAdapter(requireContext(), itemList)
                    }
                    .addOnFailureListener{ exception ->
                        Toast.makeText(requireContext(), "funding_ids 찾기 실패: ${exception.message}", Toast.LENGTH_SHORT).show()
                        Log.d("id 찾기 실패", ": ${exception.message}")
                    }
            }
            .addOnFailureListener{ exception ->
                Toast.makeText(requireContext(), "서버 데이터 획득 실패: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyJoinFundingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyJoinFundingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}