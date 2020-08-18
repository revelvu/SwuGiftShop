package com.swu.swugiftshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_make_funding_edit.*

class MakeFundingEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_funding_edit)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        storyIntentBtn.setOnClickListener {
            val editorIntent = Intent(this, MakeFundingEditActivity2::class.java)
            startActivity(editorIntent)
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
