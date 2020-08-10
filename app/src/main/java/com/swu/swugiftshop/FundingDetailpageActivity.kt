package com.swu.swugiftshop

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_funding_detailpage.*

class FundingDetailpageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funding_detailpage)

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

}