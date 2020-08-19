package com.swu.swugiftshop

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.activity_funding_detailpage2.*
import kotlinx.android.synthetic.main.funding_story2.*


//펀딩하기 버튼눌렀을때 숫자 올라가기
var sticker2numtext = 1

//var i5 = 0
var putItem6 = RecyclerItem("전자파 차단 스티커", "3000 원", "sticker")

var p6 = 0
var purchaseItem6 = purchase_RecyclerItem("전자파 차단 스티커", "3000원", " * 개", "sticket")

class FundingDetailpageActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funding_detailpage2)


        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        // tab bar
        tab_layout6.addTab(tab_layout6.newTab().setText("스토리"))
        tab_layout6.addTab(tab_layout6.newTab().setText("문의하기"))
        tab_layout6.addTab(tab_layout6.newTab().setText("리뷰"))

        val pagerAdapter6 = FragmentPagerAdapter6(supportFragmentManager, 3)
        view_pager6.adapter = pagerAdapter6

        tab_layout6.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager6.setCurrentItem(tab!!.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
        view_pager6.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout6))

       //하트 클릭시 full/empty heart 이미지 나오도록하기, 위시리스트로 들어가기
//        val emptyheart = findViewById<ImageView>(R.id.empty_heart)
//
//        emptyheart.setOnClickListener {
//
//            if(i==0){
//                emptyheart.setImageResource(R.drawable.heartfull)
//                i += 1
//
//                if(wishList.contains(inititem)) {
//                    wishList.remove(inititem)
//                }
//                wishList.add(putItem1)
//            }else {
//                emptyheart.setImageResource(R.drawable.heartempty)
//                i -= 1
//
//                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
//                wishList.remove(putItem1)
//            }
//        }


//        val fundingbtn = findViewById<Button>(R.id.fundingBtn)
//
//        // 펀딩하기 버튼을 눌렀을 때
//        fundingBtn.setOnClickListener {
//            //서포터즈+1, 펀딩완료로 setText
//            showDialog()
//            fundingBtn.setEnabled(false)
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
class FragmentPagerAdapter6(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return FundingStoryFragment2()
            }
            1 -> {
                return FundingQnAFragment2()
            }
            2 -> {
                return FundingReviewFragment2()
            }
            else -> return return FundingStoryFragment2()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}