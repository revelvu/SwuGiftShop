package com.swu.swugiftshop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment: Fragment() {

    companion object {
        const val TAG: String = "로그"
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        detailImage1.setOnClickListener {
            activity?.let {
                val intent = Intent(context, DetailpageActivity1::class.java)
                startActivity(intent)
            }
        }

        detailImage2.setOnClickListener {
            activity?.let {
                val intent = Intent(context, DetailpageActivity2::class.java)
                startActivity(intent)
            }
        }

        detailImage3.setOnClickListener {
            activity?.let {
                val intent = Intent(context, DetailpageActivity3::class.java)
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
        return view
    }
}