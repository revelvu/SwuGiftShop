package com.swu.swugiftshop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_funding_detailpage.*
import kotlinx.android.synthetic.main.official_saleslist.*


class OfficialSaleslistFragment : Fragment() {

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
    }
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }
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



