package com.swu.swugiftshop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_wishlist.*

class WishlistFragment: Fragment() {
    companion object {
        const val TAG: String = "로그"
        fun newInstance(): WishlistFragment {
            return WishlistFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wishlist, container, false)
        return view
    }
}

// Tab bar
//class TabPagerActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_wishlist)
//
//        tab_layout.addTab(tab_layout.newTab().setText("공식 굿즈"))
//        tab_layout.addTab(tab_layout.newTab().setText("비공식 굿즈"))
//
//        val pagerAdapter = FragmentPagerAdapter(supportFragmentManager, 2)
//        view_pager.adapter= pagerAdapter
//
//        tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                view_pager.setCurrentItem(tab!!.position)
//            }
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//
//
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//
//            }
//
//        })
//        // pager가 이동 했을 때 tab bar를 이동시키는 코드
//        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
//    }
//}
//
//class FragmentPagerAdapter(
//    fragmentManager: FragmentManager,
//    val tabCount: Int
//): FragmentStatePagerAdapter(fragmentManager) {
//    override fun getItem(position: Int): Fragment {
//        when(position) {
//            0 -> {
//                return WishlistFragmentOne()
//            }
//            1 -> {
//                return WishlistFragmentTwo()
//            }
//
//            else -> return return WishlistFragmentOne()
//        }
//    }
//
//    override fun getCount(): Int {
//        return tabCount
//    }
//}

