package com.swu.swugiftshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detailpage1.*
import kotlinx.android.synthetic.main.activity_detailpage2.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar


var usinumtext = 1   //유시 노트의 초기수량 == 1
var i = 0
var putItem1 = RecyclerItem("유시 유선 노트", "3000 원", "usinotecrop")

var p = 0
//var purchaseItem1 = purchase_RecyclerItem("유시 유선 노트", "3000원", " * 개", "usinotecrop")

class DetailpageActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage1)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        // tab bar
        tab_layout1.addTab(tab_layout1.newTab().setText("스토리"))
        tab_layout1.addTab(tab_layout1.newTab().setText("문의하기"))
        tab_layout1.addTab(tab_layout1.newTab().setText("리뷰"))

        val pagerAdapter1 = FragmentPagerAdapter1(supportFragmentManager, 3)
        view_pager1.adapter = pagerAdapter1

        tab_layout1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager1.setCurrentItem(tab!!.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
        view_pager1.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout1))


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
class FragmentPagerAdapter1(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return StoryFragment1()
            }
            1 -> {
                return QnAFragment()
            }
            2 -> {
                return ReviewFragment()
            }
            else -> return return StoryFragment1()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}