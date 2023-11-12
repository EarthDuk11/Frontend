package com.example.team11

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.team11.databinding.FragmentFundingBoardBinding
import com.example.team11.databinding.FragmentStreetGuideBinding
import com.google.android.material.navigation.NavigationBarView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StreetGuideFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StreetGuideFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentStreetGuideBinding

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

        binding = FragmentStreetGuideBinding.inflate(inflater, container, false)
        binding.toFunding.setOnClickListener {
            var bundle : Bundle = Bundle()
            bundle.putString("fromFrag", "펀딩 게시판으로 이동")
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            val fundingBoardFragment: Fragment = FundingBoardFragment()
            fundingBoardFragment.arguments = bundle
            transaction.replace(R.id.frameLayout, fundingBoardFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.toBrand.setOnClickListener {
            var bundle : Bundle = Bundle()
            bundle.putString("fromFrag", "홍보관 페이지로 이동")
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            val promotionFragment: Fragment = PromotionFragment()
            promotionFragment.arguments = bundle
            transaction.replace(R.id.frameLayout, promotionFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.toDiary.setOnClickListener {
            var bundle : Bundle = Bundle()
            bundle.putString("fromFrag", "일기 게시판으로 이동")
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            val diaryFragment: Fragment = DiaryFragment()
            diaryFragment.arguments = bundle
            transaction.replace(R.id.frameLayout, diaryFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

       // val bottomNavigationView = findViewById(R.id.navigationView) as NavigationBarView

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StreetGuideFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StreetGuideFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}