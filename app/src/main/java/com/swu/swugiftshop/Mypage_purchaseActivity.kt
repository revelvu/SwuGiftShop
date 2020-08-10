package com.swu.swugiftshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_mypage_purchase.*

class Mypage_purchaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_purchase)

        setSupportActionBar(my_toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)


        // tab bar
        tab_layout.addTab(tab_layout.newTab().setText("공식"))
        tab_layout.addTab(tab_layout.newTab().setText("비공식"))

        val pagerAdapter = FragmentPagerAdapter(supportFragmentManager, 2)
        view_pager.adapter = pagerAdapter

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.setCurrentItem(tab!!.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

        })
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
    }

    // tool bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.home -> {
                finish()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}

// tab bar
class FragmentPagerAdapter(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return Mypage_Purchase_official()
            }
            1 -> {
                return Mypage_Purchase_unofficial()
            }
            else -> return return Mypage_Purchase_official()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}