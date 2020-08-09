package com.swu.swugiftshop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_mypage.*

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

        return view

        //로그아웃 버튼 클릭시
        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
                //로그아웃을 성공하면, 다시 로그인 페이지

            activity?.let{
                val relogin = Intent(context, LogInActivity::class.java)
                startActivity(relogin)
            }
        }
    }


}