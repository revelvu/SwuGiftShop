package com.swu.swugiftshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_funding_detailpage.*

class DetailpageActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage3)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //하트 클릭시 full/empty heart 이미지 나오도록하기
        val emptyhearttt = findViewById<ImageView>(R.id.empty_heart)

        emptyhearttt.setOnClickListener {

            i = if(i==0){
                emptyhearttt.setImageResource(R.drawable.heartfull)
                i+1
            }else{
                emptyhearttt.setImageResource(R.drawable.heartempty)
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