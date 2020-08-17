package com.swu.swugiftshop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_story.*
import kotlinx.android.synthetic.main.mypage_purchase_official.*

class StoryFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //하트 클릭시 full/empty heart 이미지 나오도록하기
//        val emptyheart = findViewById<ImageView>(R.id.empty_heart)
        empty_heart.setOnClickListener {

            val putItem2=RecyclerItem("유시 유선 노트", "1 개", "usinotecrop")
            var i=0

            if(i==0){
                empty_heart.setImageResource(R.drawable.heartfull)
                i + 1
                wishList.add(putItem2)

            }else {
                empty_heart.setImageResource(R.drawable.heartempty)
                i - 1
                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
                wishList.remove(putItem2)
            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_story, container, false)
    }
}