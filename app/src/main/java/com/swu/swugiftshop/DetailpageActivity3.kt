package com.swu.swugiftshop

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
import kotlinx.android.synthetic.main.activity_detailpage3.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlin.properties.Delegates

var usifoldernumtext = 1 //유시 L자 폴더의 초기수량 ==1
var iii = 0
val putItem3 = RecyclerItem("유시 L자 파일", "1500 원", "usifile_crop")


class DetailpageActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage3)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        // tab bar
        tab_layout3.addTab(tab_layout3.newTab().setText("스토리"))
        tab_layout3.addTab(tab_layout3.newTab().setText("문의하기"))
        tab_layout3.addTab(tab_layout3.newTab().setText("리뷰"))

        val pagerAdapter3 = FragmentPagerAdapter3(supportFragmentManager, 3)
        view_pager3.adapter = pagerAdapter3

        tab_layout3.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager3.setCurrentItem(tab!!.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
        view_pager3.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout3))
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
class FragmentPagerAdapter3(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return StoryFragment3()
            }
            1 -> {
                return QnAFragment3()
            }
            2 -> {
                return ReviewFragment3()
            }
            else -> return return StoryFragment3()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}

