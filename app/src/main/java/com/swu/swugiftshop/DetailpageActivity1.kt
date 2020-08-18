package com.swu.swugiftshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.activity_mypage_purchase.*
import kotlinx.android.synthetic.main.navi_header.*
import kotlin.properties.Delegates


var usinumtext = 1   //유시 노트의 초기수량 == 1
var i = 0
var putItem1 = RecyclerItem("유시 유선 노트", "3000 원", "usinotecrop")

class DetailpageActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage1)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val firebaseAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        //상품info가져오기
        val productName = findViewById<TextView>(R.id.productname)
        val productPrice = findViewById<TextView>(R.id.productprice)
        val productTotalPrice = findViewById<TextView>(R.id.productTotalprice)
        var productTotalPriceShow by Delegates.notNull<Int>()

        //상품명 다른거 띄우려면 documentPath이름만 바꿔주면 됨.
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
        var usinum = findViewById<TextView>(R.id.usiNum)
//        var usinums= usinum.toString()  //수량 string값으로 변환
        var plus = findViewById<Button>(R.id.usinumPlus)
        var minus = findViewById<Button>(R.id.usinumMius)

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


        //하트 클릭시 full/empty heart 이미지 나오도록하기
        val emptyheart = findViewById<ImageView>(R.id.empty_heart)

        emptyheart.setOnClickListener {

            if (i == 0) {
                emptyheart.setImageResource(R.drawable.heartfull)
                i += 1

                wishList.add(putItem1)

            } else {
                emptyheart.setImageResource(R.drawable.heartempty)
                i -= 1

                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
                wishList.remove(putItem1)
            }
        }
    }

    // tool bar back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}