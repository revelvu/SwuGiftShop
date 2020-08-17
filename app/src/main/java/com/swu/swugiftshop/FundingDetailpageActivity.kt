package com.swu.swugiftshop

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.fragment_home.*

var i = 0

class FundingDetailpageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funding_detailpage)


        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //하트 클릭시 full/empty heart 이미지 나오도록하기
        val emptyheart = findViewById<ImageView>(R.id.empty_heart)

        emptyheart.setOnClickListener {

            i = if(i==0){
                emptyheart.setImageResource(R.drawable.heartfull)
                i+1
            }else{
                emptyheart.setImageResource(R.drawable.heartempty)
                i-1
            }
        }

        // 펀딩하기 버튼을 눌렀을 때
        fundingBtn.setOnClickListener {

        }

        // option Dialog
        optionBtn.setOnClickListener {
            var optionDialog = AlertDialog.Builder(this)
            val colors = arrayOf("화이트", "블랙")
            val checkedItem = 1

            optionDialog.setItems(colors) { dialog, which ->
                when (which) {
                    0 -> { optionBtn.setText(colors[which]) }
                    1 -> { optionBtn.setText(colors[which]) }
                }
            }
            val dialog = optionDialog.create()
            optionDialog.show()
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