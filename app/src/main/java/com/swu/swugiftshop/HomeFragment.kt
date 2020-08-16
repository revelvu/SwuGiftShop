package com.swu.swugiftshop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    companion object {
        const val TAG: String = "로그"
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    val firebaseAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        searchBtn.setOnClickListener {
            activity?.let {
                val searchIntent = Intent(context, MainListViewActivity::class.java)
                startActivity(searchIntent)
            }
        }

        detaillayout1.setOnClickListener {
            activity?.let {
                val intent = Intent(context, DetailpageActivity1::class.java)
                startActivity(intent)
            }
        }

        detaillayout2.setOnClickListener {
            activity?.let {
                val intent = Intent(context, DetailpageActivity2::class.java)
                startActivity(intent)
            }
        }

        detaillayout3.setOnClickListener {
            activity?.let {
                val intent = Intent(context, DetailpageActivity3::class.java)
                startActivity(intent)
            }
        }

        fundingDetail1.setOnClickListener {
            activity?.let {
                val intent = Intent(context, FundingDetailpageActivity::class.java)
                startActivity(intent)
            }
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
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //상품 info 가져오기 - 유시유선노트
        val productName1 = view?.findViewById<TextView>(R.id.detailname1)
        val productPrice1 = view?.findViewById<TextView>(R.id.detailprice1)
        val pName1 = db.collection("OfficialProduct").document("유시유선노트")
        pName1.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    Log.d(
                        "value",
                        "DocumentSnapshot data: " + task.result!!.data?.get("상품명")?.toString()
                    )
                    productName1?.text = task.result!!.data?.get("상품명")?.toString()
                    productPrice1?.text = task.result!!.data?.get("가격")?.toString()
                }
            }
        })

        //상품 info 가져오기 - 웬디손거울
        val productName2 = view?.findViewById<TextView>(R.id.detailname2)
        val productPrice2 = view?.findViewById<TextView>(R.id.detailprice2)
        val pName2 = db.collection("OfficialProduct").document("웬디손거울")
        pName2.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    Log.d(
                        "value",
                        "DocumentSnapshot data: " + task.result!!.data?.get("상품명")?.toString()
                    )
                    productName2?.text = task.result!!.data?.get("상품명")?.toString()
                    productPrice2?.text = task.result!!.data?.get("가격")?.toString()
                }
            }
        })
        //상품 info 가져오기 - 유시 L자 파일
        val productName3 = view?.findViewById<TextView>(R.id.detailname3)
        val productPrice3 = view?.findViewById<TextView>(R.id.detailprice3)
        val pName3 = db.collection("OfficialProduct").document("유시 L자 파일")
        pName3.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    Log.d(
                        "value",
                        "DocumentSnapshot data: " + task.result!!.data?.get("상품명")?.toString()
                    )
                    productName3?.text = task.result!!.data?.get("상품명")?.toString()
                    productPrice3?.text = task.result!!.data?.get("가격")?.toString()
                }
            }
        })
        return view
    }
}