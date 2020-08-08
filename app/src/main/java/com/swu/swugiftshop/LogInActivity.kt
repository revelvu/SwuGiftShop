package com.swu.swugiftshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_log_in.*

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

        loginBtn.setOnClickListener {
            val loginIntent = Intent(this, MainActivity::class.java)
            startActivity(loginIntent)
        }


        //로그인 버튼 눌렀을 때
//        loginBtn.setOnClickListener {
//            firebaseAuth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        Log.d(TAG, "Login Success")
//                        Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
//                        val homeIntent = Intent(this, MainActivity::class.java)
//                        startActivity(homeIntent)
//                    } else {
//                        Log.w(TAG, "Login Failure", task.exception)
//                        Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
//                        email?.setText("")
//                        password?.setText("")
//                    }
//                }
//        }
    }
}