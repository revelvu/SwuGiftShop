package com.swu.swugiftshop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.story_fragment.*

class StoryFragment : Fragment() {

        override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.story_fragment, container, false)


        //하트 클릭시 full/empty heart 이미지 나오도록하기
        val emptyheart = view.findViewById<ImageView>(R.id.empty_heart)

        emptyheart.setOnClickListener {

            if(i==0){
                empty_heart.setImageResource(R.drawable.heartfull)
                i += 1

                wishList.add(putItem1)

            }else {
                empty_heart.setImageResource(R.drawable.heartempty)
                i -= 1

                //하트 다시 비면, mutablelist에서  해당 상품 삭제하기
                wishList.remove(putItem1)
            }
        }

        //유시노트의 수량 edittext에서 가져오기
        var usinum=view.findViewById<EditText>(R.id.usiNum)
//        var usinums= usinum.toString()  //수량 string값으로 변환

        //유시노트의 수량 증가시킬 수 있는 + 버튼
        var plus= view.findViewById<Button>(R.id.usinumPlus)
        plus.setOnClickListener {
            usinumtext += 1
            usinum.setText(usinumtext.toString())
        }

        //유시노트의 수량 감소시킬 수 있는 - 버튼
        var minus=view.findViewById<Button>(R.id.usinumMius)
        minus.setOnClickListener {
            usinumtext -= 1
            usinum.setText(usinumtext.toString())
        }
        val firebaseAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        //상품info가져오기
        val productName = view.findViewById<TextView>(R.id.productname)
        val productPrice = view.findViewById<TextView>(R.id.productprice)
        val pName = db.collection("OfficialProduct").document("유시유선노트")
        pName.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    Log.d(
                        "value",
                        "DocumentSnapshot data: " + task.result!!.data?.get("상품명")?.toString()
                    )
                    productName.text = task.result!!.data?.get("상품명")?.toString()
                    productPrice.text = task.result!!.data?.get("가격")?.toString()
                }
            }
        })

        return view
    }
}