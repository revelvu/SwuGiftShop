package com.swu.swugiftshop

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detailpage2.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.funding_story1.*
import kotlinx.android.synthetic.main.funding_story1.fundingBtn
import kotlinx.android.synthetic.main.funding_story2.*


//펀딩하기 버튼눌렀을때 숫자 올라가기
var stickernumtext = 1

//var i5 = 0
var putItem5 = RecyclerItem("홀로그램 스티커", "3000 원", "sticker2")

var p5 = 0
var purchaseItem5 = purchase_RecyclerItem("홀로그램 스티커", "3000원", " * 개", "sticket2")

class FundingDetailpageActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funding_detailpage)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)



        // option Dialog
//        optionBtn.setOnClickListener {
//            var optionDialog = AlertDialog.Builder(this)
//            val colors = arrayOf("Learn to share, Share to learn", "나는 꿈꾸고 우치는 자랍니다"
//                , "Seoul Women's University", "SWU" )
//            val checkedItem = 1
//
//            optionDialog.setItems(colors) { dialog, which ->
//                when (which) {
//                    0 -> {
//                        optionBtn.setText(colors[which])
//                    }
//                    1 -> {
//                        optionBtn.setText(colors[which])
//                    }
//                    2 -> {
//                        optionBtn.setText(colors[which])
//                    }
//                    3 -> {
//                        optionBtn.setText(colors[which])
//                    }
//                }
//            }
//            val dialog = optionDialog.create()
//            optionDialog.show()
//        }

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