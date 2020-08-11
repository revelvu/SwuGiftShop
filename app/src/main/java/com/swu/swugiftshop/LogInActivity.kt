package com.swu.swugiftshop

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_mypage.*


class LogInActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val TAG: String = "loginActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        firebaseAuth = FirebaseAuth.getInstance()

        val email = findViewById<TextView>(R.id.username_inputbox)
        val password = findViewById<TextView>(R.id.password_inputbox)

        //회원가입
        registerBtn.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }


        //로그인 버튼 눌렀을 때
        loginBtn.setOnClickListener {

            makedb(password.text.toString())

            firebaseAuth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Login Success")
                        Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()


                        val homeIntent = Intent(this, MainActivity::class.java)
                        startActivity(homeIntent)
                    } else {
                        Log.w(TAG, "Login Failure", task.exception)
                        Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        email?.setText("")
                        password?.setText("")
                    }
                }
        }
    }

    fun makedb(pwd:String) {
//        lateinit var database: DatabaseReference
//        database = Firebase.database.reference

        val db = FirebaseFirestore.getInstance()    //cloud firebase 데이터베이스 생성


        db.collection(pwd)  //password(string값) 이라는 컬렉션 밑에
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                     var mynick = document.data.get("nick") as String


                    //마이페이지 액티비티로 닉네임 값 전달.
                    val mypageIntent = Intent(this, MypageActivity::class.java)
                    mypageIntent.putExtra("닉네임",mynick)

                }
            }


    }
}

