package com.swu.swugiftshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.swu.swugiftshop.MainActivity.Companion.TAG
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.navi_header.*

private lateinit var database: DatabaseReference   //실시간 데이터 베이스 생성

class RegisterActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()    //cloud firebase 데이터베이스 생성

        val email = findViewById<EditText>(R.id.email_inputbox)
        val password = findViewById<EditText>(R.id.password)
        val passwordAgain = findViewById<EditText>(R.id.reEnterPassword)

        signUpBtn.setOnClickListener {

            val nickname = findViewById<EditText>(R.id.nickName).getText().toString()   //여기에 쓰인 글자를 가져오기 getText()

            if (password.text.toString() != passwordAgain.text.toString()) {
                //두 번 입력한 비밀번호 일치하지 않는 경우
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            } else {
                firebaseAuth.createUserWithEmailAndPassword(
                    email.text.toString(),
                    password.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("createAccount", "createUserWithEmail:success")
                            Toast.makeText(this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()


                            val user = FirebaseAuth.getInstance().currentUser
                            db.collection(password.getText().toString())  //password 라는 컬렉션 밑에
                                .add(saveNickName(nickname))  //닉네임 저장  .add는 ()안에 string이나 object만 들어와야한다.


                            val loginIntent = Intent(this, LogInActivity::class.java)
                            startActivity(loginIntent)

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("createAccount", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            email?.setText("")
                            password?.setText("")
                            email.requestFocus()
                        }
                    }
            }


        }


    }

}

data class saveNickName(
    val nick: String
)


//알아서 getter,setter 만드는 클래스 'data class'

// Create a new user with a first and last name
// Add a new document with a generated ID


//db.collection("users")
//.add(user)
//.addOnSuccessListener { documentReference ->
//    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//}






