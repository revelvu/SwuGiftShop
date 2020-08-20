package com.swu.swugiftshop

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.activity_funding_detailpage2.*
import kotlinx.android.synthetic.main.funding_story2.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.properties.Delegates

//펀딩하기 버튼눌렀을때 숫자 올라가기
var sticker2numtext = 1

//var i5 = 0
var putItem6 = RecyclerItem("전자파 차단 스티커", "3000 원", "sticker")

var p6 = 0
var purchaseItem6 = purchase_RecyclerItem("전자파 차단 스티커", "3000원", " * 개", "sticket")


class FundingDetailpageActivity2 : AppCompatActivity() {
    //+-로 표시한 수량 초기넘버
    var numtobuy = 1

    //firebase
    val firebaseAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    //detail page에 필요한 정보들
    val productName: String = "전자파 차단 스티커" //이 string 변경함에 따라 물품이 달라짐.
    val fundingDatabase = db.collection("UnofficialProduct").document(productName)
    var rate by Delegates.notNull<Double>()
    lateinit var fundingDate1: String
    lateinit var fundingDate2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funding_detailpage2)


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

        //수량 TextView에서 가져오기
        var num2buy = findViewById<TextView>(R.id.num2buy)
        var plus = findViewById<Button>(R.id.plus)
        var minus = findViewById<Button>(R.id.minus)

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


        //하트 클릭시 full/empty heart 이미지 나오도록하기, 위시리스트로 들어가기
//        val emptyheart = findViewById<ImageView>(R.id.empty_heart)
//
//        emptyheart.setOnClickListener {
//
//            if(i==0){
//                emptyheart.setImageResource(R.drawable.heartfull)
//                i += 1
//
//                if(wishList.contains(inititem)) {
//                    wishList.remove(inititem)
//                }
//                wishList.add(putItem1)
//            }else {
//                emptyheart.setImageResource(R.drawable.heartempty)
//                i -= 1
//
//                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
//                wishList.remove(putItem1)
//            }
//        }


        // 펀딩하기 버튼을 눌렀을 때
//        fundingBtn.setOnClickListener {
//            //서포터즈+1, 펀딩완료로 setText
//            showDialog()
//            fundingBtn.setEnabled(false)
//        }

        optionBtn.setOnClickListener {
            var optionDialog = AlertDialog.Builder(this)
            val colors = arrayOf("화이트", "블랙")
            val checkedItem = 1
            //옵션 선택 시 텍스트 변경
            val optionColorSet = findViewById<TextView>(R.id.optionColor)

            optionDialog.setItems(colors) { dialog, which ->
                when (which) {
                    0 -> {
                        optionBtn.setText(colors[which])
                        //고른것도 바뀌게
                        optionColorSet.text = colors[which]
                    }
                    1 -> {
                        optionBtn.setText(colors[which])
                        //고른것도 바뀌게
                        optionColorSet.text = colors[which]
                    }
                }
            }
            val dialog = optionDialog.create()
            optionDialog.show()
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