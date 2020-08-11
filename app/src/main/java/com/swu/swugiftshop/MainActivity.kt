package com.swu.swugiftshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_layout.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var categoryFragment: CategoryFragment
    private lateinit var wishlistFragment: WishlistFragment
    private lateinit var messageFragment: MessageFragment
//    private lateinit var mypageFragment: MypageFragment

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


    }

    // bottom Navigation bar
    private val onBottomNaviItemSeletedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId) {
            R.id.homeicon -> {
                Log.d(TAG, "home button")
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, homeFragment).commit()
            }
            // 카테고리 버튼을 눌렀을 때
            R.id.categoryicon -> {
                Log.d(TAG, "category button")
                // 드로어 열기
//                main_drawer_layout.openDrawer(GravityCompat.START)
            }
            R.id.wishlisticon -> {
                Log.d(TAG, "wishlist button")
                wishlistFragment = WishlistFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, wishlistFragment).commit()
            }
            R.id.messageicon -> {
                Log.d(TAG, "message button")
                messageFragment = MessageFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, messageFragment).commit()
            }
            R.id.mypageicon -> {
                Log.d(TAG, "mypage button")

                val mypageIntent = Intent(this, MypageActivity::class.java)
                startActivity(mypageIntent)
//                mypageFragment = MypageFragment.newInstance()
//                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, mypageFragment).commit()
            }
        }
        true
    }

    // navigation Drawer
    class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.nav_stationery -> {
                    Toast.makeText(this, "account clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_clothing -> {
                    Toast.makeText(this, "account clicked", Toast.LENGTH_SHORT)
                    .show()
                }
                R.id.nav_accessory -> {
                    Toast.makeText(this, "account clicked", Toast.LENGTH_SHORT)
                    .show()
                }
                R.id.nav_etc -> {Toast.makeText(this, "account clicked", Toast.LENGTH_SHORT).show()
                }
            }
            main_drawer_layout.closeDrawers()
            return false
        }

        override fun onBackPressed() { //뒤로가기 처리
            if (main_drawer_layout.isDrawerOpen(GravityCompat.START)) {
                main_drawer_layout.closeDrawers()
            } else {
                super.onBackPressed()
            }
        }
    }
}

