package com.nick.sampleroom.modules.books

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nick.sampleroom.R
import com.nick.sampleroom.application.SampleRoomApplication
import com.nick.sampleroom.utils.base_class.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class BooksFragment : BaseFragment() {

    private val booksViewModels = BooksViewModels(SampleRoomApplication.getInstance())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservable()
    }

    private fun initObservable() {
        booksViewModels.bookDataList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.d("dataaaaaa", it.size.toString())
            }
        })

        booksViewModels.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        booksViewModels.loading.observe(viewLifecycleOwner, Observer {
            if(it) startDefaultLoading() else stopLoading()
        })
    }
}