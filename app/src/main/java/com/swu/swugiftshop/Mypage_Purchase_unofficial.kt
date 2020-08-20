package com.swu.swugiftshop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.mypage_purchase_official.*
import kotlinx.android.synthetic.main.mypage_purchase_unofficial.*

val inititem3=purchase_unoff_RecyclerItem("","","","")
var purchase_unofficial_list= mutableListOf(
    inititem3
)

class Mypage_Purchase_unofficial : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //이미지 클릭시 해당 상세페이지로 이동한다.
//        mypage_purchase_unofficial1.setOnClickListener {
//            activity?.let {
//                val unofficialDetailIntent = Intent(context, FundingDetailpageActivity3::class.java)
//                startActivity(unofficialDetailIntent)
//            }
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.mypage_purchase_unofficial, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val MainAdapter3 = activity?.let {
            MainAdapter3(it, purchase_unofficial_list as ArrayList<purchase_unoff_RecyclerItem>)
        }
        purchase_unoff_recycler.adapter = MainAdapter3
//        adapter.notifyDataSetChanged()

        purchase_unoff_recycler.layoutManager = LinearLayoutManager(context!!)

        purchase_unoff_recycler.setHasFixedSize(true)
    }
}