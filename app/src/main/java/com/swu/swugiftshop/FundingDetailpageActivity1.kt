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


var stickernumtext = 1
var i5 = 0
var putItem5 = RecyclerItem("홀로그램 스티커", "3000 원", "sticket2")

var p5 = 0
var purchaseItem5 = purchase_RecyclerItem("유시 유선 노트", "3000원", " * 개", "usinotecrop")

class FundingDetailpageActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funding_detailpage)


        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        // tab bar
        tab_layout.addTab(tab_layout.newTab().setText("스토리"))
        tab_layout.addTab(tab_layout.newTab().setText("문의하기"))
        tab_layout.addTab(tab_layout.newTab().setText("리뷰"))

        val pagerAdapter3 = FragmentPagerAdapter3(supportFragmentManager, 3)
        view_pager.adapter = pagerAdapter3

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.setCurrentItem(tab!!.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

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



    fun showDialog() {
        //xml자료
        var t = 0
        val fundingbutton = findViewById<Button>(R.id.fundingBtn)
        val supporters = findViewById<TextView>(R.id.supporters)
        val detailpagePercent = findViewById<TextView>(R.id.detailpagePercent)
        var num: Int = Integer.parseInt(supporters.text.toString())
        var percent: Double = (detailpagePercent.text.toString()).toDouble()

        //alert구현
        lateinit var fundingDialog: AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("펀딩하기")
        builder.setMessage("펀딩하시겠습니까?")

        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    //펀딩완료 토스트메시지
                    Toast.makeText(
                        applicationContext,
                        "펀딩이 완료되었습니다",
                        Toast.LENGTH_LONG
                    ).show()
                    //버튼을 눌렀을 때 버튼 텍스트 바꾸기
                    fundingbutton.text = "이미 펀딩한 상품입니다"
                    fundingbutton.setBackgroundColor(Color.parseColor("#424242"))
                    //버튼을 눌렀을 때 서포터 인원 +1
                    num++
                    supporters.text = num.toString()
                    //버튼 눌렀을 때 펀딩률 상승
                    percent += 2.5 //일단 40명이니까 2.5로 설정했음 db연동 필요 ㅠㅠ
                    detailpagePercent.text = percent.toString()
                }
                DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(
                    applicationContext,
                    "취소하였습니다.",
                            Toast.LENGTH_LONG
                )
                    .show()
            }

        }

        builder.setPositiveButton("펀딩하기", dialogClickListener)
        builder.setNegativeButton("취소", dialogClickListener)

        fundingDialog = builder.create()
        fundingDialog.show()
    }

}

// tab bar
class FragmentPagerAdapter3(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return FundingStoryFragment1()
            }
            1 -> {
                return FundingQnAFragment1()
            }
            2 -> {
                return FundingReviewFragment1()
            }
            else -> return return FundingStoryFragment1()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}