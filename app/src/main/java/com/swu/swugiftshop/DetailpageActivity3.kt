package com.swu.swugiftshop

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_detailpage1.*
import kotlinx.android.synthetic.main.activity_detailpage3.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlin.properties.Delegates

var iii = 0
val putItem3 = RecyclerItem("유시 L자 파일", "1500 원", "usifile_crop")


class DetailpageActivity3 : AppCompatActivity() {

    var numtobuy = 1 //초기수량 == 1
    var productTotalPriceShow by Delegates.notNull<Int>()
    lateinit var productName: TextView
    lateinit var productPrice: TextView
    lateinit var productTotalPrice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage3)

        //상품info가져오기
        productName = findViewById<TextView>(R.id.productname)
        productPrice = findViewById<TextView>(R.id.productprice)
        productTotalPrice = findViewById<TextView>(R.id.productTotalprice)
        val contentTitle = findViewById<TextView>(R.id.contentTitle)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val firebaseAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

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
                    contentTitle.text = task.result!!.data?.get("상품명")?.toString()
                    productPrice.text = task.result!!.data?.get("가격")?.toString()
                    productTotalPrice.text = task.result!!.data?.get("가격")?.toString()
                    productTotalPriceShow =
                        Integer.parseInt((productPrice.text.toString()))
                }
            }
        })
        //수량 TextView에서 가져오기
        var num2buy = findViewById<TextView>(R.id.num2buy)
        var plus = findViewById<Button>(R.id.plus)
        var minus = findViewById<Button>(R.id.minus)

        //수량 증가시킬 수 있는 + 버튼
        plus.setOnClickListener {
            numtobuy += 1
            if (numtobuy > 0) minus.setEnabled(true)
            num2buy.setText(numtobuy.toString())
            var show = productTotalPriceShow * numtobuy
            productPrice.setText(show.toString())
        }

        //수량 감소시킬 수 있는 - 버튼
        minus.setOnClickListener {
            numtobuy -= 1
            if (numtobuy == 0) minus.setEnabled(false)
            num2buy.setText(numtobuy.toString())
            var show = productTotalPriceShow * numtobuy
            productPrice.setText(show.toString())
        }

        //하트 클릭시 full/empty heart 이미지 나오도록하기
        val emptyheart = findViewById<ImageView>(R.id.empty_heart)

        emptyheart?.setOnClickListener {

            if (iii == 0) {
                emptyheart?.setImageResource(R.drawable.heartfull)
                iii += 1

                if (wishList.contains(inititem)) {
                    wishList.remove(inititem)
                }
                wishList.add(putItem3)
            } else {
                emptyheart?.setImageResource(R.drawable.heartempty)
                iii -= 1

                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
                wishList.remove(putItem3)
            }
        }

        //구매하기 버튼 클릭시, 구매 내역 페이지로 들어간다.
        val purchase = findViewById<Button>(R.id.purchaseBtn)
        purchase?.setOnClickListener {
            //버튼 한 번 클릭 -> 구매내역으로 들어감
            showDialog()
        }
    }

    fun showDialog() {
        //xml자료
        val purchaseButton = findViewById<Button>(R.id.purchaseBtn)
        var productTotalprice_t = productTotalPrice.text
        var productName_t = productName.text
        var purchaseItem = purchase_RecyclerItem(
            "$productName_t",
            "$productTotalprice_t 원",
            " $numtobuy 개",
            "usifile_crop" //image변경
        )

        //alert구현
        lateinit var purchaseDialog: AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("구매하기")
        builder.setMessage("구매하시겠습니까?")

        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    //if
                    if (purchaselist.contains(inititem2)) {
                        purchaselist.remove(inititem2)
                    }
                    purchaselist.add(purchaseItem)
                    Toast.makeText(applicationContext, "상품이 성공적으로 담겼습니다", Toast.LENGTH_LONG).show()

                    //버튼을 눌렀을 때 버튼 텍스트 바꾸기
                    purchaseButton.text = "구매 완료"
                    purchaseButton.setBackgroundColor(Color.parseColor("#424242"))
                    purchaseButton.setEnabled(false)

                }
                DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(
                    applicationContext,
                    "취소하였습니다.",
                    Toast.LENGTH_LONG
                )
                    .show()
            }

        }

        builder.setPositiveButton("구매하기", dialogClickListener)
        builder.setNegativeButton("취소", dialogClickListener)

        purchaseDialog = builder.create()
        purchaseDialog.show()
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


