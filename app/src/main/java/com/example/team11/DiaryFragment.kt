package com.example.team11

import android.app.DownloadManager
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.FragmentDiaryBinding
import com.google.firebase.firestore.Query

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiaryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentDiaryBinding
    lateinit var adapter: MyFeedAdapter
    private var isListButtonSelected = false
    private var isHashTagButtonSelected = false


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
        binding = FragmentDiaryBinding.inflate(inflater, container, false)



        return binding.root
    }


    override fun onStart() {
        super.onStart()

        makeRecyclerView()

        binding.goWritingBtn.setOnClickListener {
            activity?.let {
                val intent = Intent(context, AddDiaryActivity::class.java)
                startActivity(intent)
            }

        }
//        android:textColor="#656F77"
//        android:background="@drawable/background_style_button"

        binding.btnDiaryList.setOnClickListener {
            isListButtonSelected = true
            isHashTagButtonSelected = false
            updateButtonBackground()
            makeRecyclerView()
        }

        binding.btnHashTag.setOnClickListener {
            isHashTagButtonSelected = true
            isListButtonSelected = false
            updateButtonBackground()
            hashTagRecyclerView()

        }

    }
    private fun updateButtonBackground() {
        if (isListButtonSelected) {
            binding.btnDiaryList.setBackgroundResource(R.drawable.background_style_button)
            binding.btnHashTag.setBackgroundResource(R.drawable.background_round)
        } else {
            binding.btnDiaryList.setBackgroundResource(R.drawable.background_round)
            binding.btnHashTag.setBackgroundResource(R.drawable.background_style_button)
        }
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
                    itemList.add(item)
                }
                binding.DiaryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.DiaryRecyclerView.adapter = MyFeedAdapter(requireContext(), itemList)
            }
            .addOnFailureListener{
                Toast.makeText(requireContext(), "데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }

    }
    public fun hashTagRecyclerView() {
        MyApplication.db.collection("diaries")
            .orderBy("date", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                val itemList = mutableListOf<DiaryFeedModel>()
                for(document in result){
                    val item = document.toObject(DiaryFeedModel::class.java)
                    item.docId = document.id
                    if(item.hash == true){
                        //Toast.makeText(context, "${MyApplication.email}", Toast.LENGTH_SHORT).show()
                        item.docId = document.id
                        itemList.add(item)
                    }
                }
                binding.DiaryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.DiaryRecyclerView.adapter = MyFeedAdapter(requireContext(), itemList)
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
         * @return A new instance of fragment DiaryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiaryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}