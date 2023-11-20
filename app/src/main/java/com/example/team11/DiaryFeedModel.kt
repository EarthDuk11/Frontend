package com.example.team11


data class DiaryFeedModel(
    var docId: String? = null,
    var content: String? = null,
    var email: String? = MyApplication.email.toString(),
    var date: String? = null,
    var hash: Boolean? = true,
    var img: String? = null,
    var smileCount: Int? = 0,
    var surprisedCount: Int? = 0,
    var thumbsUpCount: Int? = 0,
    var title: String? = null,
    var uid: String? = null,
    var oneIntro : String? = null,


)