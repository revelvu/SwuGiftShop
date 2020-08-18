package com.swu.swugiftshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_detailpage1.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlin.properties.Delegates

var wendynumtext = 1 //웬디손거울 초기수량 == 1
var ii = 0
val putItem2 = RecyclerItem("웬디 손거울", "3000 원", "wendi_mirror_crop")

class DetailpageActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage2)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        // tab bar
        tab_layout.addTab(tab_layout.newTab().setText("스토리"))
        tab_layout.addTab(tab_layout.newTab().setText("문의하기"))
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
                return StoryFragment2()
            }
            1 -> {
                return QnAFragment2()
            }
            2 -> {
                return ReviewFragment2()
            }
            else -> return return StoryFragment2()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}