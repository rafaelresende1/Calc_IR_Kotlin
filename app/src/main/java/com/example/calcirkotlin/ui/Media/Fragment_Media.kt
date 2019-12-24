package com.example.calcirkotlin.ui.Media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.calcirkotlin.R

class Fragment_Media : Fragment() {
    private var viewModel_Media: ViewModel_Media? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel_Media = ViewModelProviders.of(this).get(ViewModel_Media::class.java)
        val root = inflater.inflate(R.layout.fragment_media, container, false)
        val textView = root.findViewById<TextView?>(R.id.text_media)

        viewModel_Media!!.getText()?.observe(this, Observer { s -> textView!!.setText(s) })
        return root
    }
}