package com.nick.sampleroom.modules.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nick.sampleroom.R
import com.nick.sampleroom.utils.base_class.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class BooksFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_books, container, false)
    }
}