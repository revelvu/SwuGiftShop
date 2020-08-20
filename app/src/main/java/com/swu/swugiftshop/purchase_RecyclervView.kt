package com.swu.swugiftshop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter2(val context: Context, var purchaselist: ArrayList<purchase_RecyclerItem>) :
    RecyclerView.Adapter<MainAdapter2.Holder>() {


    //화면 최초 로딩하여 xml파일을 inflate하고 ViewHolder를 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter2.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.purchaseitem_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return purchaselist.size
    }

    //위에서 만든 view와 실제 입력되는 각각의 데이터를 연결
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(purchaselist[position], context)
    }


    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val item_name = itemView?.findViewById<TextView>(R.id.Item)
        val item_price=itemView?.findViewById<TextView>(R.id.price)
        val item_num = itemView?.findViewById<TextView>(R.id.num)
        val item_image = itemView?.findViewById<ImageView>(R.id.Item_Image)
        val check_heart= itemView?.findViewById<ImageView>(R.id.checkheart)


        //bind() :ViewHolder와 클래스의 각 변수를 연동한다.
        //ex) 이쪽 TextView 에는 이 Stirng을 넣어라 라고 지정
        fun bind(purchase_recycleritem: purchase_RecyclerItem, context: Context) {
            if (purchase_recycleritem.item_image != "") {
                val resourceId = context.resources.getIdentifier(
                    purchase_recycleritem.item_image,
                    "drawable",
                    context.packageName
                )
                item_image?.setImageResource(resourceId)
            } else {
                //위시리스트에 아직 아무것도 안들어왔을때 기본 이미지 설정
//                item_image?.setImageResource(R.mipmap.ic_launcher)

                //위시리스트에 아직 아무것도 안들어왔을때!
                check_heart?.setImageResource(R.drawable.heartempty)

            }
            item_name?.text = purchase_recycleritem.item_name
            item_num?.text = purchase_recycleritem.item_num
            item_price?.text= purchase_recycleritem.item_price
        }
    }

}