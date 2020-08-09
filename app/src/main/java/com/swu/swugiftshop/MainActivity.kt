package com.swu.swugiftshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_mypage.*

class MainActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var categoryFragment: CategoryFragment
    private lateinit var wishlistFragment: WishlistFragment
    private lateinit var messageFragment: MessageFragment
    private lateinit var mypageFragment: MypageFragment

    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 하단 네비게이션 바
        bottom_nav.setOnNavigationItemSelectedListener(onBottomNaviItemSeletedListener)
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragments_frame, homeFragment).commit()


    }

    // 하단 네비게이션 바
    private val onBottomNaviItemSeletedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId) {
            R.id.homeicon -> {
                Log.d(TAG, "home button")
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, homeFragment).commit()
            }
            R.id.categoryicon -> {
                Log.d(TAG, "category button")
//                categoryFragment = CategoryFragment.newInstance()
//                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, categoryFragment).commit()
//                drawerLayout.openDrawer(GravityCompat.START)
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
                mypageFragment = MypageFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, mypageFragment).commit()
            }
        }
        true
    }

}

