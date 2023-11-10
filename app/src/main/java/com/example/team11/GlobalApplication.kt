package com.example.team11

import android.app.Application
import com.naver.maps.map.NaverMapSdk


class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // NaverMap SDK 초기화
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient("클라이언트 아이디")

    }

}