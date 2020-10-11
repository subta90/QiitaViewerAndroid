package com.example.qiitaviewerandroid.view.common

enum class RecyclerType(val int: Int) {
    HEADER(0),
    BODY(1);

    companion object {
        fun fromInt(int: Int): RecyclerType {
            return values().firstOrNull { it.int == int } ?: RecyclerType.BODY
        }
    }
}
