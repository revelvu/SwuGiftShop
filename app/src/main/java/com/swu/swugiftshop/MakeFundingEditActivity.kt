package com.swu.swugiftshop


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.activity_make_funding_edit.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MakeFundingEditActivity : AppCompatActivity() {
    //firebase
    val firebaseAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_funding_edit)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //글쓰는 유저 닉네임
        val userID = firebaseAuth.currentUser?.email.toString()
        val UserProfile =
            db.collection("UserProfile").document(userID)
        lateinit var fdNickname: String
        UserProfile.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    fdNickname = task.result!!.data?.get("nickname")?.toString().toString()
                }
            }
        })
        //카테고리
//        val fdCategory=findViewById<Button>(R.id.enterFcategory)
        lateinit var categoryText: String
        enterFcategory.setOnClickListener {
            var optionDialog = AlertDialog.Builder(this)
            val categoryOption = arrayOf("문구류", "의류", "악세사리", "기타")
            //옵션 선택 시 텍스트 변경
            optionDialog.setItems(categoryOption) { dialog, which ->
                when (which) {
                    0 -> {
                        enterFcategory.setText(categoryOption[which])
                        categoryText = enterFcategory.text.toString()
                    }
                    1 -> {
                        enterFcategory.setText(categoryOption[which])
                        categoryText = enterFcategory.text.toString()
                    }
                    2 -> {
                        enterFcategory.setText(categoryOption[which])
                        categoryText = enterFcategory.text.toString()
                    }
                    3 -> {
                        enterFcategory.setText(categoryOption[which])
                        categoryText = enterFcategory.text.toString()
                    }
                }
            }
            val dialog = optionDialog.create()
            optionDialog.show()
        }
        //EditText 가져와보자
        val fdName = findViewById<EditText>(R.id.enterFname)
        val fdDate = findViewById<EditText>(R.id.enterFdate)
        val fdMinimum = findViewById<EditText>(R.id.enterFminimum).text
        val fdPrice = findViewById<EditText>(R.id.enterFprice).text
        var fdOption = findViewById<EditText>(R.id.enterFoption)
        //옵션 추가
        val Option = ArrayList<String>()
        addOption.setOnClickListener {
            Option.add(fdOption.text.toString())
            fdOption.setText("")
        }

        storyIntentBtn.setOnClickListener {
            val editorIntent = Intent(this, MakeFundingEditActivity2::class.java)
            //여기다 db생성부 구현하자
            val fundinghash = hashMapOf(
                "펀딩주최자" to fdNickname,
                "물품명" to fdName.text.toString(),
                "마감일" to fdDate.text.toString(),
                "목표인원" to fdMinimum.toString().toInt(),
                "가격" to fdPrice.toString().toInt(),
                "카테고리" to categoryText,
                "서포터" to 0,
                "펀딩률" to 0
            )
            db.collection("UnofficialProduct").document(fdName.text.toString()).set(fundinghash)
            //db에 옵션 추가
            for (i in 0 until Option.size) {
                db.collection("UnofficialProduct").document(fdName.text.toString())
                    .update("옵션$i", Option[i])

            }
            //글쓰는 유저의 UserProfile 컬렉션에 펀딩 올린 물품명 저장하기
            UserProfile.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null) {
                        UserProfile.update("나의펀딩", "" + fdName.text.toString())
                    }
                }
            })
            editorIntent.putExtra("NAme", fdName.text.toString())
            startActivity(editorIntent)
        }
    }

    fun remainDate(fdDate: String): String {
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
        val yearRange = IntRange(0, 3)
        val monthRange = IntRange(4, 5)
        val dayRange = IntRange(6, 7)
        val differentDate =
            fdDate.slice(yearRange) + "-" + fdDate.slice(monthRange) + "-" + fdDate.slice(
                dayRange
            )
        val nextdayTimestamp: Long = todaySdf.parse(differentDate).getTime()
        val difference = nextdayTimestamp - todayTimestamp

        val remain = (difference / (24 * 60 * 60 * 1000)).toString()
        //db.collection("UnofficialProduct").document(productName).update(remainhash as Map<String, Any>)
//        db.collection("UnofficialProduct").document().update("펀딩남은시간", remain)
        Log.d("남은 일 수: ", remain)
        return remain
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
