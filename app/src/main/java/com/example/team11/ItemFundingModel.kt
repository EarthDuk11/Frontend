package com.example.team11

data class ItemFundingModel(
    var docId: String? = null,
    var email: String? = null,  // 데이터베이스에서는 email
    var title: String? = null,
    var oneIntro: String? = null,
    var content: String? = null,
    var date: String? = null,
    var link: String? = null,

    /*
    데이터베이스 속성   |   Model 속성
    content             content
    date                date
    email               email
    id                  이것도 없음.
    link                link
    oneIntro            oneIntro
    title               title
    uid                 이건 있어야 할까라는 생각이 든다.
    기본 id               docId
    * */
)
