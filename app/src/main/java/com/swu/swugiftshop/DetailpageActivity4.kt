package com.swu.swugiftshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detailpage3.*
import kotlinx.android.synthetic.main.activity_detailpage4.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar

var swufoldernumtext = 1 //슈니즈 L자 폴더의 초기수량 ==1
var iiii = 0
val putItem4 = RecyclerItem("슈니즈 L자 파일", "1500 원", "swufile_crop")

class DetailpageActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage4)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        // tab bar
        tab_layout4.addTab(tab_layout4.newTab().setText("스토리"))
        tab_layout4.addTab(tab_layout4.newTab().setText("문의하기"))
        tab_layout4.addTab(tab_layout4.newTab().setText("리뷰"))

        val pagerAdapter4 = FragmentPagerAdapter4(supportFragmentManager, 3)
        view_pager4.adapter = pagerAdapter4

        tab_layout4.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager4.setCurrentItem(tab!!.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
        view_pager4.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout4))

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
class FragmentPagerAdapter4(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return StoryFragment4()
            }
            1 -> {
                return QnAFragment4()
            }
            2 -> {
                return ReviewFragment4()
            }
            else -> return return StoryFragment4()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}