package com.example.team11
import android.Manifest
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.team11.databinding.ActivityMapViewBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource


class MapViewActivity : AppCompatActivity(), OnMapReadyCallback, Overlay.OnClickListener {

    private val LOCATION_PERMISSION_REQUEST_CODE = 5000

    private val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private lateinit var binding: ActivityMapViewBinding
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    
    // 마커 변수 선언 및 초기화
    val marker = Marker()
    val marker2 = Marker()
    val marker3 = Marker()
    val marker4 = Marker()
    val marker5 = Marker()
    val marker6 = Marker()
    val marker7 = Marker()
    val marker8 = Marker()
    val marker9 = Marker()
    val marker10 = Marker()

    val marker14 = Marker()

    val marker19 = Marker()

    val marker24 = Marker()
    val marker25 = Marker()
    val marker26 = Marker()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map_view)

        // 지도 객체 생성
        val fm = supportFragmentManager
        var mapFragment = fm.findFragmentById(R.id.mapFragment) as? MapFragment

        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance()
            fm.beginTransaction().add(R.id.mapFragment, mapFragment).commit()
        }

        // getMapAsync를 호출하여 비동기로 onMapReady 콜백 메서드 호출
        // onMapReady에서 NaverMap 객체를 받음
        mapFragment?.getMapAsync(this)

        if (!hasPermission()) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            initMapView()
        }


    }

    private fun initMapView() {
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.mapFragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.mapFragment, it).commit()
            }

        mapFragment.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    private fun hasPermission(): Boolean {
        for (permission in PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    override fun onMapReady(naverMap: NaverMap) {
        Log.d(TAG, "onMapReady")

        // 지도상에 마커 표시
        
        marker.position = LatLng(37.6500722, 127.0202356)

        marker.map = naverMap
        marker.icon = Marker.DEFAULT_ICON


        // 두 번째 마커 (예시로 추가한 마커)
        marker2.position = LatLng(37.6502939, 127.0193974)
        marker2.map = naverMap

        // 세 번째 마커 표시
        marker3.position = LatLng(37.6500048, 127.0190873)
        marker3.map = naverMap

        // 네 번째 마커 표시
        marker4.position = LatLng(37.6510458, 127.0182461)

        marker4.map = naverMap

        // 다섯 번째 마커 표시
        marker5.position = LatLng(37.652423,127.016418)
        marker5.map = naverMap

        // 여섯 번째 마커 표시
        marker6.position = LatLng(37.653281, 127.016146)
        marker6.map = naverMap

        // 일곱 번째 마커 표시
        marker7.position = LatLng(37.653225,127.015686)
        marker7.map = naverMap

        // 여덟 번째 마커 표시
        marker8.position = LatLng(37.653669,127.015346)
        marker8.map = naverMap
        // 아홉 번째 마커 표시

        marker9.position = LatLng(37.652610,127.015593)
        marker9.map = naverMap

        // 열 번째 마커 표시
        marker10.position = LatLng(37.6532749, 127.0149912)
        marker10.map = naverMap


        marker14.position = LatLng(37.6510458, 127.0182461)
        marker14.map = naverMap


        marker19.position = LatLng(37.6511085, 127.0166856)
        marker19.map = naverMap


        // md fourteen가 약학관 아트홀, (약학관 아트홀이랑 차미리사관 다시 잘 뜨게 하면 좋을듯)

        // 24 번째 마커 표시
        marker24.position = LatLng(37.6515959, 127.0166134) // 여기부터 다시 시작
        marker24.map = naverMap

        // 25번째 마커 표시
        marker25.position = LatLng(37.6519524, 127.0168511)
        marker25.map = naverMap

        // 26 번째 마커 표시
        marker26.position = LatLng(37.6519872, 127.0166375) // 요놈이 음식물쓰레기!!!!! (음식물쓰레기는 special하니까 다르게 표시해도 좋을듯)
        marker26.map = naverMap
        marker26.icon = Marker.DEFAULT_ICON
        marker26.setIconTintColor(Color.parseColor("#FF0000")) // 빨간색 설정







        marker.setOnClickListener(this);
        marker5.setOnClickListener(this);
        marker2.setOnClickListener(this);
        marker3.setOnClickListener(this);
        marker4.setOnClickListener(this);
        marker6.setOnClickListener(this);
        marker7.setOnClickListener(this);
        marker8.setOnClickListener(this);
        marker9.setOnClickListener(this);
        marker10.setOnClickListener(this);

        marker14.setOnClickListener(this);

        marker19.setOnClickListener(this);

        marker24.setOnClickListener(this);
        marker25.setOnClickListener(this);
        marker26.setOnClickListener(this);







        // 네이버 지도 현재 위치 표시
        this.naverMap = naverMap

        // 현재 위치
        naverMap.locationSource = locationSource
        // 현재 위치 버튼 기능
        naverMap.uiSettings.isLocationButtonEnabled = true
        // 위치를 추적하면서 카메라도 따라 움직인다.
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

    }


    override fun onClick(@NonNull overlay: Overlay): Boolean {
        if (overlay is Marker) {
            // 마커에 연결된 데이터 가져오기

            // 상세페이지를 표시하는 Activity로 이동
            if (overlay == marker) {
                val intent = Intent(this, MapDetailActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker2) {
                val intent = Intent(this, MDtwoActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker3) {
                val intent = Intent(this, MDthreeActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker4) {
                val intent = Intent(this, MDfourActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker5) {
                val intent = Intent(this, MDfiveActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker6) {
                val intent = Intent(this, MDsixActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker7) {
                val intent = Intent(this, MDsevenActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker8) {
                val intent = Intent(this, MDeightActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker9) {
                val intent = Intent(this, MDnineActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker10) {
                val intent = Intent(this, MDtenActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker14) {
                val intent = Intent(this, MDfourteenActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker19) {
                val intent = Intent(this, MDnineteenActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker24) {
                val intent = Intent(this, MDtwentyfourActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker25) {
                val intent = Intent(this, MDtwentyfiveActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            } else if (overlay == marker26) {
                val intent = Intent(this, MDtwentysixActivity::class.java)
                startActivity(intent)
                return true // 이벤트 소비
            }


        }
        return false
    }


}

