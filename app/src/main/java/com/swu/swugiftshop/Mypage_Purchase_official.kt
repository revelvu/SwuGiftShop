package com.swu.swugiftshop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.mypage_purchase_official.*
import kotlinx.android.synthetic.main.mypage_purchase_unofficial.*

class Mypage_Purchase_official : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mypage_purchase_official1.setOnClickListener {
            activity?.let {
                val officialDetailIntent = Intent(context, DetailpageActivity1::class.java)
                startActivity(officialDetailIntent)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.mypage_purchase_official, container, false)
    }


}