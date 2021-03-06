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
import kotlinx.android.synthetic.main.activity_funding_detailpage.fundingBtn
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.activity_my_funding.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

var putItem5 = RecyclerItem("홀로그램 스티커", "3000 원", "sticker2")
//var purchaseItem5 = purchase_RecyclerItem("홀로그램 스티커", "3000원", " * 개", "sticket2")

var p5 = 0

var p7=0

class FundingDetailpageActivity1 : AppCompatActivity() {

    var numtobuy = 1

    //firebase
    val firebaseAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    var i = 0

    //detail page에 필요한 정보들
    val productName: String = "홀로그램 스티커" //이 string 변경함에 따라 물품이 달라짐.
    val fundingDatabase = db.collection("UnofficialProduct").document(productName)
    var rate by Delegates.notNull<Double>()
    lateinit var fundingDate1: String
    lateinit var fundingDate2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funding_detailpage)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //funding page detail db에서 가져와 표시하기
        //1. 펀딩제목
        val fdTitle = findViewById<TextView>(R.id.fdTitle)
        //2. 펀딩주최자 닉네임
        val fdNickname = findViewById<TextView>(R.id.fdNickname)
        //3. 펀딩률 - 밑에 증가 반영했음
        val fdPercent = findViewById<TextView>(R.id.detailpagePercent)
        //4. 남은 시간
        val fdRemain = findViewById<TextView>(R.id.timeRemain)
        val fdEnding = findViewById<TextView>(R.id.endingDate)
        //5. 서포터 인원수 - 밑에 증가 반영했음
        val fdSupporters = findViewById<TextView>(R.id.supporters)
        //6. 펀딩 목표 인원 수
        val fdGoal = findViewById<TextView>(R.id.fundingGoal)
        //7. 펀딩물품 사이즈 or 컬러
        //8. 고른 상품 띄우기(ex. 에코백 - 블랙/ 에코백 - 화이트) -> 컬러는 optionDialog 안에 구현
        val fdName = findViewById<TextView>(R.id.productName)
        //9. 상품 가격
        val fdProductPrice = findViewById<TextView>(R.id.productTotalprice)
        var fdProductTotalPriceShow by Delegates.notNull<Int>()

        fundingDatabase.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    fdTitle.text = task.result!!.data?.get("펀딩제목")?.toString()
                    fdNickname.text = task.result!!.data?.get("펀딩주최자")?.toString()
                    fdPercent.text = task.result!!.data?.get("펀딩률").toString()
                    fdSupporters.text = task.result!!.data?.get("서포터").toString()
                    fdName.text = task.result!!.data?.get("물품명").toString()
                    fdGoal.text = task.result!!.data?.get("목표인원").toString()
                    fdProductPrice.text = task.result!!.data?.get("가격")?.toString()
                    fdProductTotalPriceShow =
                        Integer.parseInt((fdProductPrice.text.toString()))
                    val temp1 = task.result!!.data?.get("목표인원").toString()
                    rate = 100 / temp1.toDouble()

                    val temp2 = task.result!!.data?.get("마감일").toString()
                    val yearRange = IntRange(0, 3)
                    val monthRange = IntRange(4, 5)
                    val dayRange = IntRange(6, 7)
                    fundingDate1 =
                        temp2.slice(yearRange) + "-" + temp2.slice(monthRange) + "-" + temp2.slice(
                            dayRange
                        )
                    fundingDate2 =
                        temp2.slice(yearRange) + "년 " + temp2.slice(monthRange) + "월 " + temp2.slice(
                            dayRange
                        ) + "일"
                    Log.d("fundingDate", fundingDate1)
                    fdRemain.text = remainDate()
                    fdEnding.text = fundingDate2
                }
            }
        })


        // option Dialog
        val optionbtn = findViewById<Button>(R.id.optionBtn)
        optionbtn.setOnClickListener {
            var optionDialog = AlertDialog.Builder(this)
            //옵션 선택 시 내가 고른 걸로 텍스트 변경
            val showOptionText = findViewById<TextView>(R.id.showOption)
            val options = arrayOf(
                "Learn to share, Share to learn", "나는 꿈꾸고 우치는 자랍니다"
                , "Seoul Women's University", "SWU"
            )
            val checkedItem = 1

            optionDialog.setItems(options) { dialog, which ->
                when (which) {
                    0 -> {
                        optionbtn.setText(options[which])
                        showOptionText.text = options[which]
                    }
                    1 -> {
                        optionbtn.setText(options[which])
                        showOptionText.text = options[which]
                    }
                    2 -> {
                        optionbtn.setText(options[which])
                        showOptionText.text = options[which]
                    }
                    3 -> {
                        optionbtn.setText(options[which])
                        showOptionText.text = options[which]
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
        val productName = findViewById<TextView>(R.id.productName)
//        val productPrice = findViewById<TextView>(R.id.productPrice)
        val productTotalPrice = findViewById<TextView>(R.id.productTotalprice)
//        var productTotalPriceShow by Delegates.notNull<Int>()

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
//                    productPrice.text = task.result!!.data?.get("가격")?.toString()
                    productTotalPrice.text = task.result!!.data?.get("가격")?.toString()
//                    productTotalPriceShow =
//                        Integer.parseInt((productPrice.text.toString()))
                }
            }
        })


        //수량 TextView에서 가져오기
        var num2buy = findViewById<TextView>(R.id.num)
        var plus = findViewById<Button>(R.id.numPlus)
        var minus = findViewById<Button>(R.id.numMinus)

        //수량 증가시킬 수 있는 + 버튼
        plus.setOnClickListener {
            numtobuy += 1
            if (numtobuy > 0) minus.setEnabled(true)
            num2buy.setText(numtobuy.toString())
            var show = fdProductTotalPriceShow * numtobuy
            fdProductPrice.setText(show.toString())
        }

        //수량 감소시킬 수 있는 - 버튼
        minus.setOnClickListener {
            numtobuy -= 1
            if (numtobuy == 0) minus.setEnabled(false)
            num2buy.setText(numtobuy.toString())
            var show = fdProductTotalPriceShow * numtobuy
            fdProductPrice.setText(show.toString())
        }

        //구매하기[펀딩하기] 버튼 클릭시, 구매 내역 페이지로 들어간다.
        //->모두 showDialog안에서 구현하였습니다.
        val fundingBtn = findViewById<Button>(R.id.fundingBtn)
        fundingBtn.setOnClickListener {
            //서포터즈+1, 펀딩완료로 setText
            showDialog(fdProductPrice.text.toString())
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


    fun showDialog(fdProductPrice: String) {
        //xml자료
        var t = 0
        val fundingbutton = findViewById<Button>(R.id.fundingBtn)
        val supporters = findViewById<TextView>(R.id.supporters)
        val detailpagePercent = findViewById<TextView>(R.id.detailpagePercent)
        var num: Int = Integer.parseInt(supporters.text.toString())
        var percent: Double = (detailpagePercent.text.toString()).toDouble()

        var productnamee= productName

        var productTotalprice_t = fdProductPrice
        var purchase_unoff_item1 = purchase_unoff_RecyclerItem(
            "$productnamee",
            "$productTotalprice_t 원",
            " $numtobuy 개",
            "sticker2" //image주의
        )

        //alert구현
        lateinit var fundingDialog: AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("펀딩하기")
        builder.setMessage("펀딩하시겠습니까?")

        val dialogClickListener = DialogInterface.OnClickListener { _, which ->

            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    //마이페이지에서 확인 가능하게끔
                    //고쳐야겠다여기 inititem2이거
                    if (purchase_unofficial_list.contains(inititem3)) {
                            purchase_unofficial_list.remove(inititem3)
                        }
                        purchase_unofficial_list.add(purchase_unoff_item1)
                    //펀딩완료 토스트메시지
                    Toast.makeText(
                        applicationContext,
                        "펀딩이 완료되었습니다",
                        Toast.LENGTH_LONG
                    ).show()
                    //버튼을 눌렀을 때 버튼 텍스트 바꾸기
                    fundingbutton.text = "이미 펀딩한 상품입니다"
                    fundingbutton.setBackgroundColor(Color.parseColor("#424242"))
                    fundingBtn.setEnabled(false)
                    //버튼을 눌렀을 때 서포터 인원 +1
                    num += numtobuy
                    //인원 증가했음을 화면에 표시
                    supporters.text = num.toString()
                    //현재 인원 상태를 db에 갱신
                    db.collection("UnofficialProduct").document(productName).update("서포터", num)
                    //버튼 눌렀을 때 펀딩률 상승
                    percent += rate * numtobuy
                    //상승한 펀딩률 화면에 표시
                    percent = Math.round(percent * 100) / 100.0
                    detailpagePercent.text = percent.toString()
                    //현재 펀딩률 상태를 db에 갱신
                    db.collection("UnofficialProduct").document(productName).update("펀딩률", percent)
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

    fun remainDate(): String {
        //데이트포맷(일수로 구할거니깐 dd까지만 있으면됨)
        val todaySdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        //한국기준 날짜
        val calendar = Calendar.getInstance()
        val date = Date(calendar.timeInMillis)
        todaySdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"))
        val todayDate: String = todaySdf.format(date)
        //오늘 타임스탬프(데이트포맷으로 저장했다고 치고 그걸 타임스탬프로 바꿔보는 작업)
        val todayTimestamp: Long = todaySdf.parse(todayDate).getTime()
        val date2 = Date(todayTimestamp)
        val todayDate2: String = todaySdf.format(date2)
        //펀딩 마감 날짜의 타임스탬프
        val differentDate = fundingDate1
        val nextdayTimestamp: Long = todaySdf.parse(differentDate).getTime()
        val difference = nextdayTimestamp - todayTimestamp

        val remain = (difference / (24 * 60 * 60 * 1000)).toString()
        //db.collection("UnofficialProduct").document(productName).update(remainhash as Map<String, Any>)
        db.collection("UnofficialProduct").document(productName).update("펀딩남은시간", remain)

        Log.d("남은 일 수: ", remain)
        return remain
    }
}

