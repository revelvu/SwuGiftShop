package com.swu.swugiftshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detailpage.*

class DetailpageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage)

        // 펀딩하기 버튼을 눌렀을 때
        fundingBtn.setOnClickListener {

        }

    }
}