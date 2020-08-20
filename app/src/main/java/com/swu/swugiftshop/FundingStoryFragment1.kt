package com.swu.swugiftshop

import android.app.AlertDialog
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


class FundingStoryFragment1 : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var i = 0
        val view = inflater.inflate(R.layout.funding_story1, container, false)

        // option Dialog
        val optionbtn = view.findViewById<Button>(R.id.optionBtn)
        optionbtn.setOnClickListener {
            var optionDialog = AlertDialog.Builder(context)
            val colors = arrayOf(
                "Learn to share, Share to learn", "나는 꿈꾸고 우치는 자랍니다"
                , "Seoul Women's University", "SWU"
            )
            val checkedItem = 1

            optionDialog.setItems(colors) { dialog, which ->
                when (which) {
                    0 -> {
                        optionbtn.setText(colors[which])
                    }
                    1 -> {
                        optionbtn.setText(colors[which])
                    }
                    2 -> {
                        optionbtn.setText(colors[which])
                    }
                    3 -> {
                        optionbtn.setText(colors[which])
                    }
                }
            }
            val dialog = optionDialog.create()
            optionDialog.show()
        }

        //하트 클릭시 full/empty heart 이미지 나오도록하기 , 위시리스트로 들어가기
        val emptyheart = view.findViewById<ImageView>(R.id.empty_heart)

        emptyheart?.setOnClickListener {
            if (i == 0) {
                emptyheart?.setImageResource(R.drawable.heartfull)
                i += 1

                if (wishList.contains(inititem)) {
                    wishList.remove(inititem)
                }
                wishList.add(putItem5)
            } else {
                emptyheart?.setImageResource(R.drawable.heartempty)
                i -= 1

                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
                wishList.remove(putItem5)
            }
        }


        val firebaseAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        //상품info가져오기
        val productName = view.findViewById<TextView>(R.id.productname)
        val productPrice = view.findViewById<TextView>(R.id.productPrice)
        val productTotalPrice = view.findViewById<TextView>(R.id.productTotalprice)
        var productTotalPriceShow by Delegates.notNull<Int>()

        val pName = db.collection("UnofficialProduct").document("홀로그램 스티커")
        pName.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    Log.d(
                        "value",
                        "DocumentSnapshot data: " + task.result!!.data?.get("물품명")?.toString()
                    )
                    productName.text = task.result!!.data?.get("물품명")?.toString()
                    productPrice.text = task.result!!.data?.get("가격")?.toString()
                    productTotalPrice.text = task.result!!.data?.get("가격")?.toString()
                    productTotalPriceShow =
                        Integer.parseInt((productPrice.text.toString()))
                }
            }
        })



        var plus = view.findViewById<Button>(R.id.numPlus)
        var minus = view.findViewById<Button>(R.id.numMius)
        val holostickernum = view.findViewById<TextView>(R.id.num)
//        val productTotalPrice = view.findViewById<TextView>(R.id.productTotalprice)
//        var productTotalPriceShow by Delegates.notNull<Int>()

        //홀로그램 스티커 수량 증가시킬 수 있는 + 버튼 , 하단의 가격 자동 변경
        plus.setOnClickListener {
            stickernumtext += 1
            if (stickernumtext > 0) minus.setEnabled(true)
            holostickernum.setText(stickernumtext.toString())
            var show = productTotalPriceShow * stickernumtext
            productTotalPrice.setText(show.toString())
        }

        //홀로그램 스티커 수량 감소시킬 수 있는 - 버튼 , 하단의 가격 자동 변경
        minus.setOnClickListener {
            stickernumtext -= 1
            if (stickernumtext == 0) minus.setEnabled(false)
            holostickernum.setText(stickernumtext.toString())
            var show = productTotalPriceShow * stickernumtext
            productTotalPrice.setText(show.toString())
        }

        //구매하기[펀딩하기] 버튼 클릭시, 구매 내역 페이지로 들어간다.
        val purchase = view.findViewById<Button>(R.id.fundingBtn)
        purchase?.setOnClickListener {
            //버튼 한 번 클릭 -> 구매내역으로 들어감
            //버튼 그 이상 클릭 ->  "이미 담긴 상품입니다" 메세지 출력
            var productTotalprice_t = productTotalPrice.text
            var purchaseItem5 = purchase_RecyclerItem(
                "홀로그램 스티커",
                "$productTotalprice_t 원",
                " $stickernumtext 개",
                "sticker2"
            )

            if (p5 == 0) {
                if (purchaselist.contains(inititem2)) {
                    purchaselist.remove(inititem2)
                }
                purchaselist.add(purchaseItem5)
                Toast.makeText(context, "상품이 성공적으로 담겼습니다", Toast.LENGTH_LONG).show()
                p5 += 1
            } else {
                Toast.makeText(context, " 이미 담긴 상품입니다", Toast.LENGTH_LONG).show()
            }

        }
        return view
    }
}