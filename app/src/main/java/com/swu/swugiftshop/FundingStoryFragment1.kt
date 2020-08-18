package com.swu.swugiftshop

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.properties.Delegates



class FundingStoryFragment1 : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.funding_story1, container, false)

        // option Dialog
        val optionbtn = view.findViewById<Button>(R.id.optionBtn)
        optionbtn.setOnClickListener {
            var optionDialog = AlertDialog.Builder(context)
            val colors = arrayOf(
                "Learn to share, Share to learn", "나는 꿈꾸고 우치는 자랍니다"
                , "Seoul Women's University", "SWU"
            )
            val checkedItem = 1

            optionDialog.setItems(colors) { dialog, which ->
                when (which) {
                    0 -> {
                        optionbtn.setText(colors[which])
                    }
                    1 -> {
                        optionbtn.setText(colors[which])
                    }
                    2 -> {
                        optionbtn.setText(colors[which])
                    }
                    3 -> {
                        optionbtn.setText(colors[which])
                    }
                }
            }
            val dialog = optionDialog.create()
            optionDialog.show()
        }




        return view
    }


}