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
import kotlinx.android.synthetic.main.funding_story2.*
import kotlin.properties.Delegates

class FundingStoryFragment2 : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var i = 0

        val view = inflater.inflate(R.layout.funding_story2, container, false)


        //하트 클릭시 full/empty heart 이미지 나오도록하기 , 위시리스트로 들어가기
        val emptyheart = view.findViewById<ImageView>(R.id.empty_heart)

        emptyheart?.setOnClickListener {
            if (i == 0) {
                emptyheart?.setImageResource(R.drawable.heartfull)
                i += 1

                if (wishList.contains(inititem)) {
                    wishList.remove(inititem)
                }
                wishList.add(putItem6)
            } else {
                emptyheart?.setImageResource(R.drawable.heartempty)
                i -= 1

                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
                wishList.remove(putItem6)
            }
        }


        var plus = view.findViewById<Button>(R.id.numPlus)
        var minus = view.findViewById<Button>(R.id.numMius)
        val holostickernum = view.findViewById<TextView>(R.id.num)
        val productTotalPrice = view.findViewById<TextView>(R.id.productTotalprice)
        var productTotalPriceShow by Delegates.notNull<Int>()

        //전자파 차단 스티커 수량 증가시킬 수 있는 + 버튼 , 하단의 가격 자동 변경
        plus.setOnClickListener {
            sticker2numtext += 1
            if (sticker2numtext > 0) minus.setEnabled(true)
            holostickernum.setText(sticker2numtext.toString())
            var show = productTotalPriceShow * sticker2numtext
            productTotalPrice.setText(show.toString())
        }

        //전자파 차단 스티커 수량 감소시킬 수 있는 - 버튼 , 하단의 가격 자동 변경
        minus.setOnClickListener {
            sticker2numtext -= 1
            if (sticker2numtext == 0) minus.setEnabled(false)
            holostickernum.setText(sticker2numtext.toString())
            var show = productTotalPriceShow * sticker2numtext
            productTotalPrice.setText(show.toString())
        }

        //구매하기[펀딩하기] 버튼 클릭시, 구매 내역 페이지로 들어간다.
        val purchase = view.findViewById<Button>(R.id.fundingBtn)
        purchase?.setOnClickListener {
            //버튼 한 번 클릭 -> 구매내역으로 들어감
            //버튼 그 이상 클릭 ->  "이미 담긴 상품입니다" 메세지 출력
            var productTotalprice_t = productTotalPrice.text
            var purchaseItem5 = purchase_RecyclerItem(
                "전자파 차단 스티커",
                "$productTotalprice_t 원",
                " $sticker2numtext 개",
                "sticker"
            )

            if (p6 == 0) {
                if (purchaselist.contains(inititem2)) {
                    purchaselist.remove(inititem2)
                }
                purchaselist.add(purchaseItem6)
                Toast.makeText(context, "상품이 성공적으로 담겼습니다", Toast.LENGTH_LONG).show()
                p6 += 1
            } else {
                Toast.makeText(context, " 이미 담긴 상품입니다", Toast.LENGTH_LONG).show()
            }

        }

        return view
    }


}