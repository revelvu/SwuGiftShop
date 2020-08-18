package com.swu.swugiftshop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.mypage_purchase_unofficial.*

class Mypage_Purchase_unofficial : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mypage_purchase_unofficial1.setOnClickListener {
            activity?.let {
                val unofficialDetailIntent = Intent(context, FundingDetailpageActivity1::class.java)
                startActivity(unofficialDetailIntent)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.mypage_purchase_unofficial, container, false)
    }

}