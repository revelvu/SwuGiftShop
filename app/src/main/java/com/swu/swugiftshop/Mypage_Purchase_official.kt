package com.swu.swugiftshop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_wishlist.*
import kotlinx.android.synthetic.main.mypage_purchase_official.*
import kotlinx.android.synthetic.main.mypage_purchase_unofficial.*

val inititem2=purchase_RecyclerItem("","","","")
var purchaselist= mutableListOf(
    inititem2
)

class Mypage_Purchase_official : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //* 기존) 해당 아이템 누르면 상품 상세페이지로 이동한다.
//        mypage_purchase_official1.setOnClickListener {
//            activity?.let {
//                val officialDetailIntent = Intent(context, DetailpageActivity1::class.java)
//                startActivity(officialDetailIntent)
//            }
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.mypage_purchase_official, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter2 =  activity?.let { MainAdapter2(it, purchaselist as ArrayList<purchase_RecyclerItem>) }
        purchase_recycler.adapter = mAdapter2
//        adapter.notifyDataSetChanged()

        purchase_recycler.layoutManager= LinearLayoutManager(context!!)

        purchase_recycler.setHasFixedSize(true)


    }
}