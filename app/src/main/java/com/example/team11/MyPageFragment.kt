package com.example.team11

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team11.databinding.FragmentMyPageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [MyPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding : FragmentMyPageBinding

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
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        binding.userEmail.text = MyApplication.email + "님"

        // button 클릭시 fragmenrFive_ReviwList로 이동
        binding.diaryBtn.setOnClickListener { // 람다식 리스너 setOnclickListener{}
            goFragment(MyWritingFragment())
        }
        binding.fundingJoiningBtn.setOnClickListener { // 람다식 리스너 setOnclickListener{}
            goFragment(MyJoinFundingFragment())
        }
        binding.fundingOpeningBtn.setOnClickListener { // 람다식 리스너 setOnclickListener{}
            goFragment(MyOpenFundingFragment())
        }
        binding.userEmail.text = MyApplication.email

        binding.logoutBtn.setOnClickListener {
            //로그아웃...........
            MyApplication.auth.signOut()
            MyApplication.email = null
            Toast.makeText(activity, "로그아웃되었습니다.", Toast.LENGTH_LONG).show()
            // 여기에서 MainFragment로 전환하고 싶음. 
        }


        return binding.root
    }


    fun goFragment(fragment : Fragment)  {
        var bundle : Bundle = Bundle()
        bundle.putString("fromFrag", "프래그먼트1")
        val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragment.arguments = bundle
        transaction.replace(R.id.frameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}