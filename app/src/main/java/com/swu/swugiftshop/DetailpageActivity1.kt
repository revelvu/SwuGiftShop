package com.swu.swugiftshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.activity_mypage_purchase.*
import kotlinx.android.synthetic.main.navi_header.*

class DetailpageActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage1)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        // tab bar
        tab_layout.addTab(tab_layout.newTab().setText("스토리"))
        tab_layout.addTab(tab_layout.newTab().setText("문의사항"))
        tab_layout.addTab(tab_layout.newTab().setText("리뷰"))

        val pagerAdapter2 = FragmentPagerAdapter2(supportFragmentManager, 3)
        view_pager.adapter = pagerAdapter2

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.setCurrentItem(tab!!.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))


        //하트 클릭시 full/empty heart 이미지 나오도록하기
//        val emptyheart = findViewById<ImageView>(R.id.empty_heart)
//
//        emptyheart.setOnClickListener {
//
//            val putItem2=RecyclerItem("유시 유선 노트", "1 개", "usinotecrop")
//            var i=0
//
//            if(i==0){
//                emptyheart.setImageResource(R.drawable.heartfull)
//                i+1
//
//                wishList.add(putItem2)
//
//            }else {
//                emptyheart.setImageResource(R.drawable.heartempty)
//                i - 1
//
//                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
//                wishList.remove(putItem2)
//            }
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

// tab bar
class FragmentPagerAdapter2(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return StoryFragment()
            }
            1 -> {
                return QnAFragment()
            }
            2 -> {
                return ReviewFragment()
            }
            else -> return return StoryFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}