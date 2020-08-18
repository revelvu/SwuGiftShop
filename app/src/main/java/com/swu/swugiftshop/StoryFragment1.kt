package com.swu.swugiftshop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.properties.Delegates

class StoryFragment1 : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.story_fragment, container, false)

        //하트 클릭시 full/empty heart 이미지 나오도록하기
        val emptyheart = view.findViewById<ImageView>(R.id.empty_heart)

        emptyheart?.setOnClickListener {

            if (i == 0) {
                emptyheart?.setImageResource(R.drawable.heartfull)
                i += 1

                if (wishList.contains(inititem)) {
                    wishList.remove(inititem)
                }
                wishList.add(putItem1)
            } else {
                emptyheart?.setImageResource(R.drawable.heartempty)
                i -= 1

                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
                wishList.remove(putItem1)
            }
        }

        //구매하기 버튼 클릭시, 구매 내역 페이지로 들어간다.
        val purchase = view.findViewById<Button>(R.id.fundingBtn)
        purchase?.setOnClickListener {
            //버튼 한 번 클릭 -> 구매내역으로 들어감
            //버튼 그 이상 클릭 ->  "이미 담긴 상품입니다" 메세지 출력

            if (p == 0) {
                if (purchaselist.contains(inititem2)) {
                    purchaselist.remove(inititem2)
                }
                purchaselist.add(purchaseItem1)
                Toast.makeText(context, "상품이 성공적으로 담겼습니다", Toast.LENGTH_LONG).show()
                p += 1
            } else {
                Toast.makeText(context, " 이미 담긴 상품입니다", Toast.LENGTH_LONG).show()
            }
        }

//        //유시노트의 수량 edittext에서 가져오기
//        var usinum1 = view.findViewById<EditText>(R.id.usiNum)
////        var usinums= usinum.toString()  //수량 string값으로 변환
//
//        //유시노트의 수량 증가시킬 수 있는 + 버튼
//        var plus1 = view.findViewById<Button>(R.id.usinumPlus)
//        plus1.setOnClickListener {
//            usinumtext += 1
//            usinum1.setText(usinumtext.toString())
//        }
//
//        //유시노트의 수량 감소시킬 수 있는 - 버튼
//        var minus1 = view.findViewById<Button>(R.id.usinumMius)
//        minus1.setOnClickListener {
//            usinumtext -= 1
//            usinum1.setText(usinumtext.toString())
//        }

        val firebaseAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        //상품info가져오기
        val productName = view.findViewById<TextView>(R.id.productname)
        val productPrice = view.findViewById<TextView>(R.id.productprice)
        val productTotalPrice = view.findViewById<TextView>(R.id.productTotalprice)
        var productTotalPriceShow by Delegates.notNull<Int>()

        val pName = db.collection("OfficialProduct").document("유시유선노트")
        pName.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    Log.d(
                        "value",
                        "DocumentSnapshot data: " + task.result!!.data?.get("상품명")?.toString()
                    )
                    productName.text = task.result!!.data?.get("상품명")?.toString()
                    productPrice.text = task.result!!.data?.get("가격")?.toString()
                    productTotalPrice.text = task.result!!.data?.get("가격")?.toString()
                    productTotalPriceShow =
                        Integer.parseInt((productPrice.text.toString()))
                }
            }
        })

        //유시노트의 수량 TextView에서 가져오기
        var usinum=view.findViewById<TextView>(R.id.usiNum)
//        var usinums= usinum.toString()  //수량 string값으로 변환
        var plus= view.findViewById<Button>(R.id.usinumPlus)
        var minus=view.findViewById<Button>(R.id.usinumMius)

        //유시노트의 수량 증가시킬 수 있는 + 버튼
        plus.setOnClickListener {
            usinumtext += 1
            if (usinumtext > 0) minus.setEnabled(true)
            usinum.setText(usinumtext.toString())
            var show = productTotalPriceShow * usinumtext
            productTotalPrice.setText(show.toString())
        }

        //유시노트의 수량 감소시킬 수 있는 - 버튼
        minus.setOnClickListener {
            usinumtext -= 1
            if (usinumtext == 0) minus.setEnabled(false)
            usinum.setText(usinumtext.toString())
            var show = productTotalPriceShow * usinumtext
            productTotalPrice.setText(show.toString())
        }


        return view
    }
}