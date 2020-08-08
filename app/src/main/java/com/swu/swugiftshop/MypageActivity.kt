package com.swu.swugiftshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_mypage.*

class MypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_mypage)


        // 로그아웃 버튼을 눌렀을 때
        logoutBtn.setOnClickListener {
            // 로그인 화면으로 화면 전환
            val logoutIntent = Intent(this, LogInActivity::class.java)
            startActivity(logoutIntent)
        }
    }
}