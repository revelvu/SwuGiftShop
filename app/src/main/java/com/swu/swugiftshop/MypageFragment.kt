package com.swu.swugiftshop

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.android.synthetic.main.fragment_mypage.*
import java.lang.ClassCastException


//class MypageActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_mypage)
//
//        val nick = findViewById<TextView>(R.id.myNickName)
////
//        val mypageIntent = Intent()
//        val MyNickName = mypageIntent.getStringExtra("닉네임")
////        nick.text = "$MyNickName 슈니"
//        nick.text = "$MyNickName 슈니"
//
//
//
//        val logoutbtn = findViewById<Button>(R.id.logoutBtn)
//
//        logoutbtn.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            //로그아웃을 성공하면, 다시 로그인 페이지!
//
//            val relogin = Intent(this, LogInActivity::class.java)
//            startActivity(relogin)
//
//        }
//
//
//    }
//
//}


class MypageFragment : Fragment() {
    companion object {
        const val TAG: String = "로그"
        fun newInstance(): MypageFragment {
            return MypageFragment()
        }
    }

    interface OnUserProfileSetListener {
        fun userProfileSet(nickname: String, email: String)
    }

    val firebaseAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
//    var listener: OnUserProfileSetListener? = null
//    lateinit var onUserProfileSetListener: OnUserProfileSetListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        listener = context as? OnUserProfileSetListener
////        onUserProfileSetListener.userProfileSet(myNickName.toString(), myEmail.toString())
//        if (listener == null) {
//            throw ClassCastException("$context must implement OnUserProfileSetListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)

        //프로필에 사용자 email 띄우기
        val myEmail = view?.findViewById<TextView>(R.id.myEmail)
        val userID = firebaseAuth.currentUser?.email.toString()
        myEmail?.text = userID
        val noteEmail: String = myEmail.toString()

        //프로필에 사용자 nickname 띄우기
        val myNickname = view?.findViewById<TextView>(R.id.myNickName)
        val userNickname =
            db.collection("UserProfile").document(userID)
        userNickname.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    Log.d(
                        "value",
                        "DocumentSnapshot data: " + task.result!!.data?.get("nickname")?.toString()
                    )
                    myNickname?.text = task.result!!.data?.get("nickname")?.toString()
//                    val noteNickname: String = myNickname.toString()
//                    listener?.userProfileSet(noteNickname, noteEmail)
                } else {
                    Log.d("value", "No such document")
                }
            } else {
                Log.d("value", "get failed with ", task.exception)
            }
        })



        val purchasebtn = view.findViewById<Button>(R.id.purchaseBtn)
        purchasebtn.setOnClickListener {
            activity?.let {
                val intent = Intent(context, Mypage_purchaseActivity::class.java)
                startActivity(intent)
            }
        }

        val makeFundingbtn = view.findViewById<Button>(R.id.makeFundingBtn)
        makeFundingbtn.setOnClickListener {
            activity?.let {
                val intent = Intent(context, EmptyFundingActivity::class.java)
                startActivity(intent)
            }
        }

        val logoutbtn = view.findViewById<Button>(R.id.logoutBtn)
        logoutbtn.setOnClickListener {
            val logoutBuilder = AlertDialog.Builder(context)
            logoutBuilder.setTitle("로그아웃")
            logoutBuilder.setMessage("정말 로그아웃 하시겠어요?")

            logoutBuilder.setPositiveButton("확인") { dialog, which ->
            FirebaseAuth.getInstance().signOut()
            //로그아웃을 성공하면, 다시 로그인 페이지!

            activity?.let {
                val relogin = Intent(context, LogInActivity::class.java)
                startActivity(relogin)
            }
        }
            logoutBuilder.setNegativeButton("취소", null)

            val dialog = logoutBuilder.create()
            dialog.show()
        }
        return view
    }
}

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//
//        makedb()
//
//    }
//
//    fun makedb() {
//////        lateinit var database: DatabaseReference
//////        database = Firebase.database.reference
////
////        val db = FirebaseFirestore.getInstance()    //cloud firebase 데이터베이스 생성
////
//////        val nickname = getView()!!.findViewById<TextView>(R.id.myNickName)
////        val password = activity!!.findViewById<EditText>(R.id.password_inputbox).text.toString()
////
////
////        db.collection(password)  //password(string값) 이라는 컬렉션 밑에
////            .get()
////            .addOnSuccessListener { result ->
////
////                for (document in result) {
////                    var mynick = document.data["nick"].toString()
////
//////                    val mynick=document.data["nick"].toString()    //여기서 값을 못꺼내오고 있는거같음  ->uid가 공유가 안되고있음!
//////                    var mynick=document.get("nick").toString()
//////                    mynick= document.data.get("nick") as String
////
////                    //마이 페이지의 닉네임이 저절로 바뀌어야 함
////                    myNickName!!.setText("$mynick 슈니")    //mynick이 지금 null 이래.... setText(CharSequence text)
////
////                }
////            }
////    }
//}
//
//
//


