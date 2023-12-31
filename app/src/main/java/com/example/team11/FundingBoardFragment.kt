package com.example.team11

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team11.databinding.FragmentFundingBoardBinding
import com.example.team11.databinding.FragmentStreetGuideBinding
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.Query

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FundingBoardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FundingBoardFragment : Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentFundingBoardBinding

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
        binding = FragmentFundingBoardBinding.inflate(inflater, container, false)
        binding.fundingWriteButton.setOnClickListener {
            val intent = Intent(requireContext(), FundingWriteActivity::class.java)
            startActivity(intent)
        }
        
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        // 원래는 DB에서 데이터를 가져와야 함. 지금은 테스트용 코드를 작성한 것.

//        val itemList = mutableListOf<ItemFundingModel>()
//        val item1 : ItemFundingModel = ItemFundingModel()
//        item1.docId="1"
//        item1.writer="환경운동가"
//        item1.title = "펀딩 모집"
//        item1.oneLine = "분리배출 원활화 위한 쓰레기통 제작"
//        item1.content = "안녕하세요. 펀딩을 하려고 합니다. 안녕하세요. 펀딩을 하려고 합니다. 안녕하세요. 펀딩을 하려고 합니다."
//        item1.date="2023-11-08"
//        if (item1 != null) {
//            itemList.add(item1)
//            Log.d("fundingBoard", "item1 저장 완료")
//        }
//
//        val item2 : ItemFundingModel = ItemFundingModel()
//        item2.docId="2"
//        item2.writer="환경운동가2"
//        item2.title = "마스크 재활용을 위한 펀딩 모집"
//        item2.oneLine = "마스크 재활용을 위한 펀딩"
//        item2.content = "안녕하세요. 펀딩을 하려고 합니다. 안녕하세요. 펀딩을 하려고 합니다. 안녕하세요. 펀딩을 하려고 합니다."
//        item2.date="2023-11-08"
//        if (item2 != null) {
//            itemList.add(item2)
//            Log.d("fundingBoard", "item2 저장 완료")
//        }
//
//        binding.fundingBoardRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        binding.fundingBoardRecyclerView.adapter = MyFundingAdapter(requireContext(), itemList)
//
//        if(itemList.size==0){
//            Log.d("fundingBoard", "아이템 리스트가 "+ itemList.size.toString()+" 개임. ")
//        }

// 지금부터 DB에서 가져올 것임.

        MyApplication.db.collection("favorite")
            .whereEqualTo("user_email", MyApplication.email)
            .get()
            .addOnSuccessListener { result ->
                val fundingIds = result.documents.map { it.getString("funding_id") }
                // 사용자가 즐겨찾기를 누른 funding 게시글의 funding_id에 해당하는 게시글을 들고 옴.
                MyApplication.db.collection("fundings")
                    .orderBy("date", Query.Direction.DESCENDING)
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
                        binding.fundingBoardRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                        binding.fundingBoardRecyclerView.adapter = MyFundingAdapter(requireContext(), itemList)
                    }
                    .addOnFailureListener{ exception ->
                        Toast.makeText(requireContext(), "funding_ids 찾기 실패: ${exception.message}", Toast.LENGTH_SHORT).show()
                        Log.d("id 찾기 실패", ": ${exception.message}")
                    }
            }
            .addOnFailureListener{ exception ->
                Toast.makeText(requireContext(), "서버 데이터 획득 실패: ${exception.message}", Toast.LENGTH_SHORT).show()
            }



//        Log.d("Funding Board", "onStart")
//        MyApplication.db.collection("fundings")
//
//            .orderBy("date", Query.Direction.DESCENDING)
//
//            .get()
//            .addOnSuccessListener { result ->
//                val itemList = mutableListOf<ItemFundingModel>()
//                for(document in result){
//                    val item = document.toObject(ItemFundingModel::class.java)
//                    item.docId = document.id
//                    itemList.add(item)
//                }
//                binding.fundingBoardRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//                binding.fundingBoardRecyclerView.adapter = MyFundingAdapter(requireContext(), itemList)
//            }
//            .addOnFailureListener{ exception ->
//                Toast.makeText(requireContext(), "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
//            }



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FundingBoardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FundingBoardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}