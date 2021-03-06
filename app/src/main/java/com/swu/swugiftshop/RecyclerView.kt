package com.swu.swugiftshop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.swu.swugiftshop.R.id.*

class MainAdapter(val context: Context, var wishlist: ArrayList<RecyclerItem>) :
    RecyclerView.Adapter<MainAdapter.Holder>() {


    //화면 최초 로딩하여 xml파일을 inflate하고 ViewHolder를 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.wishitem_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return wishlist.size
    }

    //위에서 만든 view와 실제 입력되는 각각의 데이터를 연결
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(wishlist[position], context)
    }


    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val emptyWishlist = itemView?.findViewById<TextView>(empty_wishlist)
        val item_name = itemView?.findViewById<TextView>(ItemName)
        val item_num = itemView?.findViewById<TextView>(Num)
        val item_image = itemView?.findViewById<ImageView>(Item_Image)
        val check_heart= itemView?.findViewById<ImageView>(checkheart)


        //bind() :ViewHolder와 클래스의 각 변수를 연동한다.
        //ex) 이쪽 TextView 에는 이 Stirng을 넣어라 라고 지정
        fun bind(recycleritem: RecyclerItem, context: Context) {
            if (recycleritem.item_image != "") {
                val resourceId = context.resources.getIdentifier(
                    recycleritem.item_image,
                    "drawable",
                    context.packageName
                )
                item_image?.setImageResource(resourceId)
            } else {
                //위시리스트에 아직 아무것도 안들어왔을때 기본 이미지 설정
//                item_image?.setImageResource(R.mipmap.ic_launcher)

                //위시리스트에 아직 아무것도 안들어왔을때!
                check_heart?.setImageResource(R.drawable.heartempty)
                item_name?.text="담긴 물품이 없습니다."

            }
            item_name?.text = recycleritem.item_name
            item_num?.text = recycleritem.item_price
        }
    }

}