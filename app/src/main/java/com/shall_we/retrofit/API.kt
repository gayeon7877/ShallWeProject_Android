package com.shall_we.retrofit

object API {
    const val BASE_URL : String = "http://43.201.110.47/"

    const val EXPERIENCE_GIFT = "api/v1/experience/gift"

    const val EXPERIENCE_GIFT_STTCATEGORY = "api/v1/experience/gift/stt-category/{SttCategoryId}"

    const val EXPERIENCE_GIFT_EXPCATEGORY = "api/v1/experience/gift/exp-category/{ExpCategoryId}"
}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}