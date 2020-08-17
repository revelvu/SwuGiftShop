package com.swu.swugiftshop

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView


//RecyclerView에 담을 데이터 클래스를 생성
class RecyclerItem(
    val item_name: String,
    val num : String,
    val item_image : String  //drawable에 들어갈 이미지 파일의 이름
)

