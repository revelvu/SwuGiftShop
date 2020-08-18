package com.swu.swugiftshop

import android.content.Intent
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

var ii=0
val putItem2= RecyclerItem("웬디 손거울","3000 원","wendi_mirror_crop")

class DetailpageActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage2)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        var wendynum=findViewById<EditText>(R.id.wendiNum)

        //웬디 손거울의 수량 증가시킬 수 있는 + 버튼
        var plus= findViewById<Button>(R.id.plus)
        plus.setOnClickListener {
            usinumtext += 1
            wendynum.setText(usinumtext.toString())
        }

        //웬디 손거울의 수량 감소시킬 수 있는 - 버튼
        var minus=findViewById<Button>(R.id.minus)
        minus.setOnClickListener {
            usinumtext -= 1
            wendynum.setText(usinumtext.toString())
        }

        val firebaseAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        //상품info가져오기
        val productName = findViewById<TextView>(R.id.productname)
        val productPrice = findViewById<TextView>(R.id.productprice)
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
                }
            }
        })

        //하트 클릭시 full/empty heart 이미지 나오도록하기
        val emptyheartt = findViewById<ImageView>(R.id.empty_heart)

        emptyheartt.setOnClickListener {

            if(ii==0){
                emptyheartt.setImageResource(R.drawable.heartfull)
                ii+=1

                //하트가 채워지면, recyclerview item들 중에서 해당 데이터가 추가되어야한다.
                if(wishList.contains(inititem)) {
                    wishList.remove(inititem)
                }
                wishList.add(putItem2)

            }else{
                emptyheartt.setImageResource(R.drawable.heartempty)
                ii-=1

                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
                wishList.remove(putItem2)
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