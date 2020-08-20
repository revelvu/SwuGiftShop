package com.swu.swugiftshop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.unofficial_saleslist.*
import kotlinx.android.synthetic.main.unofficial_saleslist2.*

class UnofficialSaleslistFragment2 : Fragment() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.unofficial_saleslist2, container, false)
        return view
    }

    companion object {
        const val TAG: String = "로그"
        fun newInstance(): UnofficialSaleslistFragment2 {
            return UnofficialSaleslistFragment2()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        unofficial_salelist4.setOnClickListener {
            activity?.let {
                val intent = Intent(context, FundingDetailpageActivity4::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        val fundingPerLeft = view?.findViewById<TextView>(R.id.viewRateLeft)
        val fLeft = db.collection("UnofficialProduct").document("2020 SS 꽃학잠")
        fLeft.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    fundingPerLeft?.text = task.result!!.data?.get("펀딩률")?.toString()
                }
            }
        })
    }

    //
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.official_saleslist, container, false)
//        return view
//    }

}



