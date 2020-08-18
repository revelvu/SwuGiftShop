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
var purchaseItem1 = purchase_RecyclerItem("유시 유선 노트", "3000원", " * 개", "usinotecrop")

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



//        //유시노트의 수량 edittext에서 가져오기
//        var usinum=findViewById<EditText>(R.id.usiNum)
////        var usinums= usinum.toString()  //수량 string값으로 변환
//
//        //유시노트의 수량 증가시킬 수 있는 + 버튼
//        var plus= findViewById<Button>(R.id.usinumPlus)
//        plus.setOnClickListener {
//            usinumtext += 1
//            usinum.setText(usinumtext.toString())
//        }
//
//        //유시노트의 수량 감소시킬 수 있는 - 버튼
//        var minus=findViewById<Button>(R.id.usinumMius)
//        minus.setOnClickListener {
//            usinumtext -= 1
//            usinum.setText(usinumtext.toString())
//        }
//        val firebaseAuth = FirebaseAuth.getInstance()
//        val db = FirebaseFirestore.getInstance()
//
//        //상품info가져오기
//        val productName = findViewById<TextView>(R.id.productname)
//        val productPrice = findViewById<TextView>(R.id.productprice)
//        val pName = db.collection("OfficialProduct").document("유시유선노트")
//        pName.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
//            if (task.isSuccessful) {
//                val document = task.result
//                if (document != null) {
//                    Log.d(
//                        "value",
//                        "DocumentSnapshot data: " + task.result!!.data?.get("상품명")?.toString()
//                    )
//                    productName.text = task.result!!.data?.get("상품명")?.toString()
//                    productPrice.text = task.result!!.data?.get("가격")?.toString()
//                }
//            }
//        })

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