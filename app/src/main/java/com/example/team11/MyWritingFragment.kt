package com.example.team11

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team11.databinding.FragmentDiaryBinding
import com.example.team11.databinding.FragmentMyWritingBinding
import com.google.firebase.firestore.Query

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyWritingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyWritingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding : FragmentMyWritingBinding

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
        binding = FragmentMyWritingBinding.inflate(inflater, container, false)
        makeRecyclerView()

        return binding.root
    }
    public fun makeRecyclerView() {
        MyApplication.db.collection("diaries")
            .orderBy("date", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                val itemList = mutableListOf<DiaryFeedModel>()
                for(document in result){
                    val item = document.toObject(DiaryFeedModel::class.java)
                    item.docId = document.id
                    if(MyApplication.email.equals(item.email)){
                        itemList.add(item)
                    }

                }
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerView.adapter = MyFeedAdapter(requireContext(), itemList)
            }
            .addOnFailureListener{
                Toast.makeText(requireContext(), "데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyWritingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyWritingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}