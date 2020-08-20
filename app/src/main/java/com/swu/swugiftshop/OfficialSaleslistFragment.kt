package com.swu.swugiftshop

import android.content.Context
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
import kotlinx.android.synthetic.main.official_saleslist.*

class OfficialSaleslistFragment : Fragment() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.official_saleslist, container, false)

        return view
    }

    companion object {
        const val TAG: String = "로그"
        fun newInstance(): OfficialSaleslistFragment {
            return OfficialSaleslistFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        val fundingPerLeft = view?.findViewById<TextView>(R.id.viewRateLeft)
        val fLeft = db.collection("UnofficialProduct").document("홀로그램 스티커")
        fLeft.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    fundingPerLeft?.text = task.result!!.data?.get("펀딩률")?.toString()
                }
            }
        })

        val fundingPerRight = view?.findViewById<TextView>(R.id.viewRateRight)
        val fRight = db.collection("UnofficialProduct").document("전자파 차단 스티커")
        fRight.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    fundingPerRight?.text = task.result!!.data?.get("펀딩률")?.toString()
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        official_salelist1.setOnClickListener {
            activity?.let {
                val Intent = Intent(context, DetailpageActivity1::class.java)
                startActivity(Intent)
            }
        }

        official_salelist2.setOnClickListener {
            activity?.let {
                val Intent = Intent(context, DetailpageActivity2::class.java)
                startActivity(Intent)
            }
        }

        official_salelist3.setOnClickListener {
            activity?.let {
                val Intent = Intent(context, DetailpageActivity3::class.java)
                startActivity(Intent)
            }
        }

        official_salelist4.setOnClickListener {
            activity?.let {
                val Intent = Intent(context, DetailpageActivity4::class.java)
                startActivity(Intent)
            }
        }
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



