package com.swu.swugiftshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_funding_detailpage.*

class DetailpageActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage2)


        // 펀딩하기 버튼을 눌렀을 때
        fundingBtn.setOnClickListener {
            // 펀딩률에서 숫자랑 %기호랑 텍스트뷰를 각각 따로 만들어서 %기호 신경쓰지 마시고 숫자만 바꾸시면 돼요!
            // 서포터도 펀딩률이랑 마찬가지에요
        }
    }
}