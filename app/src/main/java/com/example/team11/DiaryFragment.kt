package com.example.team11

import android.app.DownloadManager
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team11.databinding.FragmentDiaryBinding

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

//        binding.menuSearch.setOnClickListener {
//            val intent = Intent(requireContext(), ReviewSearchActivity::class.java)
//            startActivity(intent)
//        }

        return binding.root
    }


    override fun onStart() {
        super.onStart()
        val dummyDataList = listOf(
            DiaryFeedModel("1", "user1@example.com", "제목 1", "내용 1"),
            DiaryFeedModel("1", "user2@example.com", "제목 2", "내용 2"),
            DiaryFeedModel("1", "user3@example.com", "제목 3", "내용 3"),
            DiaryFeedModel("1", "user1@example.com", "제목 1", "내용 1"),
            DiaryFeedModel("1", "user2@example.com", "제목 2", "내용 2"),
            DiaryFeedModel("1", "user3@example.com", "제목 3", "내용 3")
        )

        val itemList = mutableListOf<DiaryFeedModel>()
        for (item in dummyDataList) {
            itemList.add(item)
        }
        binding.DiaryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.DiaryRecyclerView.adapter = MyFeedAdapter(requireContext(), itemList)

        binding.goWritingBtn.setOnClickListener{
            activity?.let{
                val intent = Intent(context, AddDiaryActivity::class.java)
                startActivity(intent)
            }

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