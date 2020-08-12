package com.swu.swugiftshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.activity_mypage_purchase.*
import kotlinx.android.synthetic.main.navi_header.*

var i=0

class DetailpageActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage1)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)


        //하트 클릭시 full/empty heart 이미지 나오도록하기
        val emptyheart = findViewById<ImageView>(R.id.empty_heart)

        emptyheart.setOnClickListener {

            i = if(i==0){
                emptyheart.setImageResource(R.drawable.heartfull)
                i+1
            }else{
                emptyheart.setImageResource(R.drawable.heartempty)
                i-1
            }
        }
    }

    // tool bar back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}