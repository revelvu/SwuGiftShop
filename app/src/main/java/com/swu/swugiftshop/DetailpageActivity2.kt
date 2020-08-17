package com.swu.swugiftshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_funding_detailpage.*

class DetailpageActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage2)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)


        //하트 클릭시 full/empty heart 이미지 나오도록하기
        val emptyheartt = findViewById<ImageView>(R.id.empty_heart)

        emptyheartt.setOnClickListener {

            val putItem1= RecyclerItem("웬디 손거울","1 개","wendi_mirror_crop")
            var i=0

            if(i==0){
                emptyheartt.setImageResource(R.drawable.heartfull)
                i+1

                //하트가 채워지면, recyclerview item들 중에서 해당 데이터가 추가되어야한다.
                wishList.add(putItem1)

            }else{
                emptyheartt.setImageResource(R.drawable.heartempty)
                i-1

                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
                wishList.remove(putItem1)
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