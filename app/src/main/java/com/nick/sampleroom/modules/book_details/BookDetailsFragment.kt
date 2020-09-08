package com.nick.sampleroom.modules.book_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.nick.sampleroom.R
import com.nick.sampleroom.databinding.FragmentBookDetailsBinding
import com.nick.sampleroom.modules.books.model.BookDataModel
import com.nick.sampleroom.utils.base_class.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class BookDetailsFragment : BaseFragment() {

    private lateinit var bindData: FragmentBookDetailsBinding
    private var bookDataModel: BookDataModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindData = DataBindingUtil.inflate(inflater, R.layout.fragment_book_details, container, false)
        return bindData.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewAndData()
    }

    private fun initViewAndData() {
        getIntentData()
        setData()
    }

    private fun setData() {
        bindData.book = bookDataModel
    }

    private fun getIntentData() {
        arguments?.let {
            this@BookDetailsFragment.bookDataModel = BookDetailsFragmentArgs.fromBundle(it).bookData
        }
    }
}