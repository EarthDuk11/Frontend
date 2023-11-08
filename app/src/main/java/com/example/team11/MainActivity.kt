package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.team11.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){

    private lateinit var fundingBoardFragment: FundingBoardFragment
    private lateinit var fundingDetailFragment: FundingDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fundingBoardFragment = FundingBoardFragment()
        fundingDetailFragment = FundingDetailFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fundingBoardFragment)
            .commit()

        val bottomNavigationView = findViewById(R.id.navigationView) as NavigationBarView
        bottomNavigationView.setOnItemSelectedListener(
            object: NavigationBarView.OnItemSelectedListener{
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    var selectedFragment: Fragment ? = null
                    when(item.itemId){
                        R.id.home_menu -> selectedFragment = fundingBoardFragment // 원래는 지도 프레그먼트, 지금은 테스트용
                      R.id.menu_menu -> selectedFragment = fundingDetailFragment // 원래는 길잡이 프레그먼트로 해야 함. 지금은 테스트로 detailFragment를 잡아놓음.
                   //     R.id.mypage_menu = 마이페이지 액티비티
                    }
                    selectedFragment?.let{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frameLayout, selectedFragment)
                            .commit()
                        return true
                    }
                    return false
                }
            }
        )


    }
}