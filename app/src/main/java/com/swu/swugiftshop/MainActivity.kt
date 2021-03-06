package com.swu.swugiftshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
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
    private lateinit var unofficialSaleslistFragment: UnofficialSaleslistFragment
    private lateinit var unofficialSaleslistFragment2: UnofficialSaleslistFragment2
    lateinit var navView: NavigationView

    companion object {
        const val TAG: String = "로그"
    }

    val firebaseAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

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
                    val d = drawer.findViewById<NavigationView>(R.id.nav_view)
                    val e = d.findViewById<TextView>(R.id.mynickname)
                    val f = d.findViewById<TextView>(R.id.myemail)
                    val userID = firebaseAuth.currentUser?.email.toString()
                    f.text = userID
                    val userNickname =
                        db.collection("UserProfile").document(userID)
                    userNickname.get()
                        .addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
                            if (task.isSuccessful) {
                                val document = task.result
                                if (document != null) {
                                    e.text =
                                        task.result!!.data?.get("nickname")?.toString()
                                } else {
                                    Log.d("value", "No such document")
                                }
                            } else {
                                Log.d("value", "get failed with ", task.exception)
                            }
                        })
                    drawer.openDrawer(GravityCompat.START)
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
            R.id.unoffi_stationery -> {
                unofficialSaleslistFragment = UnofficialSaleslistFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragments_frame, unofficialSaleslistFragment).commit()
            }
            R.id.unoffi_clothing -> {
                unofficialSaleslistFragment2 = UnofficialSaleslistFragment2.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragments_frame, unofficialSaleslistFragment2).commit()
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


}


