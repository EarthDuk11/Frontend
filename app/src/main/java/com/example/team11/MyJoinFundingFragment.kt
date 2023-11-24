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
     var favorite_docId : String? = ""
    var itemList = mutableListOf<ItemFundingModel>()

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
        makeRecyclerView()
        Log.d("test", "test")

        return binding.root
    }
    public fun makeRecyclerView() {
        MyApplication.db.collection("favorite")
            .get()
            .addOnSuccessListener { result ->
                Toast.makeText(context, "test : ${favorite_docId}", Toast.LENGTH_SHORT).show()

                for(document in result){
                    val item = document.toObject(ItemFavoriteFundingModel::class.java)
                    if(MyApplication.email.equals(item.user_email)){

                        favorite_docId = item.funding_id.toString()
                        getFavoriteFunding(favorite_docId!!)
                        Log.d("myjoinfunding", "id: ${favorite_docId}")
                        Log.d("myjoinfunding", "myJoinFunding user_id : ${item.user_email}")

                    }

                }


            }
            .addOnFailureListener{
                Toast.makeText(requireContext(), "데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }

    }
    public fun getFavoriteFunding(funding_id : String) {
        MyApplication.db.collection("fundings")
            .orderBy("date", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    val item = document.toObject(ItemFundingModel::class.java)
                    item.docId = document.id
                    if (document.id.equals(funding_id)) {
                        Log.d("myjoinfunding", "get f_id : ${item.docId}")
                        Log.d("myjoinfunding", "myJoinFundging funding docId : ${item.docId}")

                        itemList.add(item)
                    }
                    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyclerView.adapter = MyFundingAdapter(requireContext(), itemList)

                }

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