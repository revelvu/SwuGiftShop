package com.swu.swugiftshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_layout.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.my_toolbar
import kotlinx.android.synthetic.main.fragment_mypage.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var homeFragment: HomeFragment
    private lateinit var wishlistFragment: WishlistFragment
    private lateinit var mypageFragment: MypageFragment
    private lateinit var officialSaleslistFragment: OfficialSaleslistFragment
    lateinit var navView: NavigationView

    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // tool bar
        setSupportActionBar(my_toolbar)


        // bottom Navigation bar
        bottom_nav.setOnNavigationItemSelectedListener(onBottomNaviItemSeletedListener)
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragments_frame, homeFragment).commit()

        // navigation Drawer
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
    }

    // bottom Navigation bar
    private val onBottomNaviItemSeletedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeicon -> {
                    homeFragment = HomeFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragments_frame, homeFragment).commit()
                }
                R.id.categoryicon -> {
                    var drawer = findViewById<DrawerLayout>(R.id.drawer_Layout)
                    drawer.openDrawer(GravityCompat.START)
//                    val d = drawer.findViewById<NavigationView>(R.id.nav_view)
//                    val e = d.findViewById<TextView>(R.id.mynickname)
//                    val f = d.findViewById<TextView>(R.id.myemail)
//                    val info = Info()


                }
                R.id.wishlisticon -> {
                    wishlistFragment = WishlistFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragments_frame, wishlistFragment).commit()
                }
                R.id.mypageicon -> {
                    mypageFragment = MypageFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragments_frame, mypageFragment).commit()

//                    val mainIntent = getIntent()
//                    val NickName = mainIntent.getStringExtra("닉네임")

//                    val goMainIntent = Intent(this, MypageFragment::class.java)
//                    goMainIntent.putExtra("닉네임", NickName)
//
//                    startActivity(goMainIntent)

                }
            }
            true
        }

    // navigation Drawer
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_stationery -> {
                officialSaleslistFragment = OfficialSaleslistFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragments_frame, officialSaleslistFragment).commit()
            }
            R.id.nav_clothing -> {

            }
            R.id.nav_accessory -> {

            }
            R.id.nav_etc -> {
            }
        }
        drawer_Layout.closeDrawers()
        return false
    }

    override fun onBackPressed() { //뒤로가기 처리
        if (drawer_Layout.isDrawerOpen(GravityCompat.START)) {
            drawer_Layout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }

//    class Info : MypageFragment.OnUserProfileSetListener {
//        override fun userProfileSet(nickname: String, email: String) {
//            val nm = nickname
//            val em = email
//            Log.d("nm", nm)
//            Log.d("em", nm)
//        }
//
//    }


}


