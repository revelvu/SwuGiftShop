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
import kotlinx.android.synthetic.main.story_fragment2.*
import kotlin.properties.Delegates

class StoryFragment2 : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.story_fragment2, container, false)

        val firebaseAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        //상품info가져오기
        val productName = view.findViewById<TextView>(R.id.productname)
        val productPrice = view.findViewById<TextView>(R.id.productprice)
        val productTotalPrice = view.findViewById<TextView>(R.id.productTotalprice)
        var productTotalPriceShow by Delegates.notNull<Int>()

        //상품명 다른거 띄우려면 documentPath이름만 바꿔주면 됨.
        val pName = db.collection("OfficialProduct").document("웬디손거울")
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

        //웬디 손거울의 수량 TextView에서 가져오기
        var wendinum = view.findViewById<TextView>(R.id.wendiNum)
        var plus = view.findViewById<Button>(R.id.wendinumPlus)
        var minus = view.findViewById<Button>(R.id.wendinumMius)

        //웬디 손거울의 수량 증가시킬 수 있는 + 버튼
        plus.setOnClickListener {
            wendynumtext += 1
            if (wendynumtext > 0) minus.setEnabled(true)
            wendinum.setText(wendynumtext.toString())
            var show = productTotalPriceShow * wendynumtext
            productTotalPrice.setText(show.toString())
        }

        //웬디 손거울의 수량 감소시킬 수 있는 - 버튼
        minus.setOnClickListener {
            wendynumtext -= 1
            if (wendynumtext == 0) minus.setEnabled(false)
            wendinum.setText(wendynumtext.toString())
            var show = productTotalPriceShow * wendynumtext
            productTotalPrice.setText(show.toString())
        }

        //하트 클릭시 full/empty heart 이미지 나오도록하기
        val emptyhearttt = view.findViewById<ImageView>(R.id.empty_heart)

        emptyhearttt.setOnClickListener {
            if (ii == 0) {
                emptyhearttt.setImageResource(R.drawable.heartfull)
                ii += 1

                if (wishList.contains(inititem)) {
                    wishList.remove(inititem)
                }
                wishList.add(putItem2)

            } else {
                emptyhearttt.setImageResource(R.drawable.heartempty)
                ii -= 1

                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
                wishList.remove(putItem2)
            }

        }

        return view
    }
}