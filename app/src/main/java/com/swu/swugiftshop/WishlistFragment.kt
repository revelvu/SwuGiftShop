package com.swu.swugiftshop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_wishlist.*

var wishList = mutableListOf(
    //데이터 추가 기능 만들기 전 일단 임의 리스트 생성
    //(이름,수량,이미지(String)) 순서로 작성
    RecyclerItem(" ", "", "")
//    RecyclerItem("웬디 손거울","2","wendi_mirror_crop"),
//    RecyclerItem("유시 L자 파일","3","usifile_crop")
)



class WishlistFragment : Fragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var wishList = mutableListOf(
//            //데이터 추가 기능 만들기 전 일단 임의 리스트 생성
//            //(이름,수량,이미지(String)) 순서로 작성
//            RecyclerItem("유시 유선 노트", "1", "usinotecrop"),
//            RecyclerItem("웬디 손거울","2","wendi_mirror_crop"),
//            RecyclerItem("유시 L자 파일","3","usifile_crop")
//        )

        val mAdapter =  activity?.let { MainAdapter(it, wishList as ArrayList<RecyclerItem>) } //여긴 왜 arrayList죠?
        wish_recycler.adapter = mAdapter
//        adapter.notifyDataSetChanged()

        wish_recycler.layoutManager=LinearLayoutManager(context!!)

        wish_recycler.setHasFixedSize(true)
    }
}






