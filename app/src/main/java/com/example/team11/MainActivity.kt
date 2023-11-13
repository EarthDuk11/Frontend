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

        mainFragment = MainFragment()

        streetGuideFragment = StreetGuideFragment()

        mypageFragment = MyPageFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, mainFragment)
            .commit()

        val bottomNavigationView = findViewById(R.id.navigationView) as NavigationBarView
        bottomNavigationView.setOnItemSelectedListener(
            object: NavigationBarView.OnItemSelectedListener{
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    var selectedFragment: Fragment ? = null
                    when(item.itemId){
                        R.id.home_menu -> selectedFragment = mainFragment // 원래는 지도 프레그먼트, 지금은 테스트용
                        R.id.menu_menu -> selectedFragment = streetGuideFragment // 원래는 길잡이 프레그먼트로 해야 함. 지금은 테스트로 detailFragment를 잡아놓음.
                        R.id.mypage_menu -> selectedFragment = mypageFragment
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