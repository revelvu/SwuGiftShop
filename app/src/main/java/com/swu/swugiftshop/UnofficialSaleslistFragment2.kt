package com.swu.swugiftshop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.unofficial_saleslist.*
import kotlinx.android.synthetic.main.unofficial_saleslist2.*

class UnofficialSaleslistFragment2 : Fragment() {

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



