package com.swu.swugiftshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.activity_make_funding_edit2.*

class MakeFundingEditActivity2 : AppCompatActivity() {
    //firebase
    val firebaseAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_funding_edit2)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //EditText 가져오기
        val fdTitle = findViewById<EditText>(R.id.enterFtitle)
        val fdContent = findViewById<EditText>(R.id.enterFcontent)
        val fdName = intent.getStringExtra("NAme")

        storyIntentBtn.setOnClickListener {
            //db생성부 구현하기
            db.collection("UnofficialProduct").document(fdName)
                .update("펀딩제목", fdTitle.text.toString())
            db.collection("UnofficialProduct").document(fdName)
                .update("내용", fdContent.text.toString())
            Toast.makeText(
                baseContext, "펀딩이 등록되었습니다.",
                Toast.LENGTH_SHORT
            ).show()
            //펀딩 글쓰기 끝내면 화면전환을 어디로 해야하지??????????????????????
        }
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