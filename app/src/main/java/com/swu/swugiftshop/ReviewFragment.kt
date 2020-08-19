package com.swu.swugiftshop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.review_fragment.*

class ReviewFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.review_fragment, container, false)

        val reviewEditorbtn = view.findViewById<Button>(R.id.reviewEditorBtn)
        reviewEditorbtn.setOnClickListener {
            activity?.let {
                val intent = Intent(context, ReviewEditorActivity1::class.java)
                startActivity(intent)
            }
        }

        return view
    }
}