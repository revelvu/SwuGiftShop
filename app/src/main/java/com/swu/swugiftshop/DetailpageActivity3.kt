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
import kotlin.properties.Delegates

var usifoldernumtext = 1 //유시 L자 폴더의 초기수량 ==1
var iii = 0
val putItem3 = RecyclerItem("유시 L자 파일", "1500 원", "usifile_crop")


class DetailpageActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage3)

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
        val pName = db.collection("OfficialProduct").document("유시 L자 파일")
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

        //유시 L자 파일의 수량 TextView에서 가져오기
        var usiLnum = findViewById<TextView>(R.id.usiLnum)
        var plus = findViewById<Button>(R.id.plus2)
        var minus = findViewById<Button>(R.id.minus2)

        //유시 L자 파일의 수량 증가시킬 수 있는 + 버튼
        plus.setOnClickListener {
            usifoldernumtext += 1
            if (usifoldernumtext > 0) minus.setEnabled(true)
            usiLnum.setText(usifoldernumtext.toString())
            var show = productTotalPriceShow * usifoldernumtext
            productTotalPrice.setText(show.toString())
        }

        //유시 L자 파일의 수량 감소시킬 수 있는 - 버튼
        minus.setOnClickListener {
            usifoldernumtext -= 1
            if (usifoldernumtext == 0) minus.setEnabled(false)
            usiLnum.setText(usifoldernumtext.toString())
            var show = productTotalPriceShow * usifoldernumtext
            productTotalPrice.setText(show.toString())
        }

        //하트 클릭시 full/empty heart 이미지 나오도록하기
        val emptyhearttt = findViewById<ImageView>(R.id.empty_heart)

        emptyhearttt.setOnClickListener {
            if (iii == 0) {
                emptyhearttt.setImageResource(R.drawable.heartfull)
                iii += 1

                if(wishList.contains(inititem)) {
                    wishList.remove(inititem)
                }
                wishList.add(putItem3)

            } else {
                emptyhearttt.setImageResource(R.drawable.heartempty)
                iii -= 1

                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
                wishList.remove(putItem3)
            }

        }

        // tool bar back button
        fun onOptionsItemSelected(item: MenuItem): Boolean {
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
}