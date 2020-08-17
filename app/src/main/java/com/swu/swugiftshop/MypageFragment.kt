package com.swu.swugiftshop

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)


        val purchasebtn = view.findViewById<Button>(R.id.purchaseBtn)
        purchasebtn.setOnClickListener {
            activity?.let {
                val Intent = Intent(context, Mypage_purchaseActivity::class.java)
                startActivity(Intent)
            }
        }


        val makeFundingbtn = view.findViewById<Button>(R.id.makeFundingBtn)
        makeFundingbtn.setOnClickListener {
            activity?.let {
                val Intent = Intent(context, EmptyFundingActivity::class.java)
                startActivity(Intent)
            }
        }


        // logout dialog
        val logoutbtn = view.findViewById<Button>(R.id.logoutBtn)
        logoutbtn.setOnClickListener {
            val logoutBuilder = AlertDialog.Builder(context)
            logoutBuilder.setTitle("로그아웃")
            logoutBuilder.setMessage("정말 로그아웃 하시겠어요?")

            logoutBuilder.setPositiveButton("OK") { dialog, which ->

                FirebaseAuth.getInstance().signOut()
                //로그아웃을 성공하면, 다시 로그인 페이지!

                activity?.let {
                    val relogin = Intent(context, LogInActivity::class.java)
                    startActivity(relogin)
                }

            }
            logoutBuilder.setNegativeButton("Cancel", null)

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


