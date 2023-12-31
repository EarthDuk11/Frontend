package com.example.team11

import android.content.Intent
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

    private lateinit var mainFragment: MainFragment
    private lateinit var streetGuideFragment: StreetGuideFragment
    private lateinit var mypageFragment : MyPageFragment

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) } // 여기까지 함 (11/9)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // diary permission add 다음에...해야함


        mainFragment = MainFragment()

        streetGuideFragment = StreetGuideFragment()

        mypageFragment = MyPageFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, mainFragment)
            .commit()

        // FundingDetailActivity에서 FundingBoardActivity로 돌아오고자 할 때
        val fragmentToFundingBoardFragment = intent.getStringExtra("fragmentToFundingBoard")

        // 해당 프레그먼트로 이동

        if(fragmentToFundingBoardFragment == "FundingBoardFragment"){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, FundingBoardFragment())
                .commit()
        }

        val bottomNavigationView = findViewById<NavigationBarView>(R.id.navigationView)
        bottomNavigationView.setOnItemSelectedListener(
            object: NavigationBarView.OnItemSelectedListener{
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    var selectedFragment: Fragment ? = null
                    when(item.itemId){
                        R.id.home_menu -> selectedFragment = mainFragment
                        R.id.menu_menu -> {
                            // 여기서 menu_menu를 눌렀을 때 LoginActivity로 이동하도록 설정 -> 로그인이 되어있다면 streetGuideFragment로 이동하게 해야 함.
                            if(!MyApplication.checkAuth()){
                                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                                selectedFragment= mainFragment
                                startActivity(intent)
                                return true
                            }
                            selectedFragment= streetGuideFragment

                        }
                        R.id.mypage_menu -> {
                            // 여기서 mypage_menu 눌렀을 때 LoginActivity로 이동하도록 설정 -> 로그인이 되어있다면 mypageFragment로 이동하게 해야 함.
                            if(!MyApplication.checkAuth()){
                                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                                selectedFragment= mainFragment
                                startActivity(intent)
                                return true
                            }
                            selectedFragment= mypageFragment

                        }

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

        /*
        binding.seeMapBtn.setOnClickListener {   //seeMapBtn 클릭 시

            //2-2. Fragment로 연결해서 현재 액티비티에 띄우기
            val naverMapFragment = NaverMapFragment()
              supportFragmentManager.beginTransaction()
                  .add(R.id.mapContent, naverMapFragment).commit()
        }
        */

    }


    fun onMapBoxClick(view: View) {
        // map_box를 클릭했을 때 수행할 동작을 여기에 추가
        val intent = Intent(this, MapViewActivity::class.java)
        startActivity(intent)
    }

}