package com.swu.swugiftshop

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlin.properties.Delegates


//펀딩하기 버튼눌렀을때 숫자 올라가기
var stickernumtext = 1

//var i5 = 0
var putItem5 = RecyclerItem("홀로그램 스티커", "3000 원", "sticker2")

var p5 = 0
//var purchaseItem5 = purchase_RecyclerItem("홀로그램 스티커", "3000원", " * 개", "sticker2")

var p7=0

class FundingDetailpageActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funding_detailpage)


        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)


        // option Dialog
        val optionbtn = findViewById<Button>(R.id.optionBtn)
        optionbtn.setOnClickListener {
            var optionDialog = AlertDialog.Builder(this)
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
        val emptyheart = findViewById<ImageView>(R.id.empty_heart)

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
        val productName = findViewById<TextView>(R.id.productname)
        val productPrice = findViewById<TextView>(R.id.productPrice)
        val productTotalPrice = findViewById<TextView>(R.id.productTotalprice)
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


        var plus = findViewById<Button>(R.id.numPlus)
        var minus = findViewById<Button>(R.id.numMius)
        val holostickernum = findViewById<TextView>(R.id.num)
//        val productTotalPrice = findViewById<TextView>(R.id.productTotalprice)
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
        val purchase_unofficial = findViewById<Button>(R.id.fundingBtn)
        purchase_unofficial?.setOnClickListener {
            //버튼 한 번 클릭 -> 구매내역으로 들어감
            //버튼 그 이상 클릭 ->  "이미 담긴 상품입니다" 메세지 출력
            var productTotalprice_t = productTotalPrice.text
            var purchase_unoffItem5 = purchase_unoff_RecyclerItem(
                "홀로그램 스티커",
                "$productTotalprice_t 원",
                "$stickernumtext 개",
                "sticker2"
            )

            if (p7 == 0) {
                if (purchase_unofficial_list.contains(inititem3)) {
                    purchase_unofficial_list.remove(inititem3)
                }
                purchase_unofficial_list.add(purchase_unoffItem5)
                Toast.makeText(this, "상품이 성공적으로 담겼습니다", Toast.LENGTH_LONG).show()
                p7 += 1
            } else {
                Toast.makeText(this, " 이미 담긴 상품입니다", Toast.LENGTH_LONG).show()
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



    fun showDialog() {
        //xml자료
        var t = 0
        val fundingbutton = findViewById<Button>(R.id.fundingBtn)
        val supporters = findViewById<TextView>(R.id.supporters)
        val detailpagePercent = findViewById<TextView>(R.id.detailpagePercent)
        var num: Int = Integer.parseInt(supporters.text.toString())
        var percent: Double = (detailpagePercent.text.toString()).toDouble()

        //alert구현
        lateinit var fundingDialog: AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("펀딩하기")
        builder.setMessage("펀딩하시겠습니까?")

        val dialogClickListener = DialogInterface.OnClickListener { _, which ->

            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    //펀딩완료 토스트메시지
                    Toast.makeText(
                        applicationContext,
                        "펀딩이 완료되었습니다",
                        Toast.LENGTH_LONG
                    ).show()
                    //버튼을 눌렀을 때 버튼 텍스트 바꾸기
                    fundingbutton.text = "이미 펀딩한 상품입니다"
                    fundingbutton.setBackgroundColor(Color.parseColor("#424242"))
                    //버튼을 눌렀을 때 서포터 인원 +1
                    num++
                    supporters.text = num.toString()
                    //버튼 눌렀을 때 펀딩률 상승
                    percent += 2.5 //일단 40명이니까 2.5로 설정했음 db연동 필요 ㅠㅠ
                    detailpagePercent.text = percent.toString()

                }
                DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(
                    applicationContext,
                    "취소하였습니다.",
                            Toast.LENGTH_LONG
                )
                    .show()
            }

        }

        builder.setPositiveButton("펀딩하기", dialogClickListener)
        builder.setNegativeButton("취소", dialogClickListener)

        fundingDialog = builder.create()
        fundingDialog.show()
    }

}

