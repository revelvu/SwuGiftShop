package com.swu.swugiftshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.activity_funding_detailpage4.*

class FundingDetailpageActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funding_detailpage4)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        // tab bar
        tab_layout8.addTab(tab_layout8.newTab().setText("스토리"))
        tab_layout8.addTab(tab_layout8.newTab().setText("문의하기"))
        tab_layout8.addTab(tab_layout8.newTab().setText("리뷰"))

        val pagerAdapter8 = FragmentPagerAdapter8(supportFragmentManager, 3)
        view_pager8.adapter = pagerAdapter8

        tab_layout8.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager8.setCurrentItem(tab!!.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
        view_pager8.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout8))
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
class FragmentPagerAdapter8(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return FundingStoryFragment4()
            }
            1 -> {
                return FundingQnAFragment4()
            }
            2 -> {
                return FundingReviewFragment4()
            }
            else -> return return FundingStoryFragment4()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}