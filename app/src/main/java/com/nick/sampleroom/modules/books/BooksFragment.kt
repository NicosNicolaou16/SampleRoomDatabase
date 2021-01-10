package com.nick.sampleroom.modules.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.nick.sampleroom.R
import com.nick.sampleroom.application.SampleRoomApplication
import com.nick.sampleroom.databinding.FragmentBooksBinding
import com.nick.sampleroom.modules.books.adapter.BookAdapter
import com.nick.sampleroom.modules.books.model.BookDataModel
import com.nick.sampleroom.utils.base_classes.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class BooksFragment : BaseFragment(), BookAdapter.BookListener {

    private lateinit var booksViewModels: BooksViewModels
    private var bookAdapter: BookAdapter? = null
    private var binding: FragmentBooksBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBooksBinding.bind(view)
        booksViewModels = ViewModelProvider(this).get(BooksViewModels::class.java)

        init()
    }

    private fun init() {
        initAdapter()
        //createDummyData()
        initObservable()
    }

    private fun createDummyData() {
        booksViewModels.createTheDataWithLiveData()
    }

    private fun initAdapter() {
        this.bookAdapter = BookAdapter(mutableListOf(), this)
        binding?.booksListAdapter?.apply {
            adapter = this@BooksFragment.bookAdapter
        }
    }

    private fun initObservable() {
        booksViewModels.bookDataList.observe(viewLifecycleOwner, Observer {
            this.bookAdapter?.loadData(it)
        })

        /*booksViewModels.bookDataListLiveData.observe(viewLifecycleOwner, Observer {
            this.bookAdapter?.loadData(it)
        })*/

        booksViewModels.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        booksViewModels.loading.observe(viewLifecycleOwner, Observer {
            if (it) startDefaultLoading() else stopLoading()
        })
    }

    override fun onClickBookListener(bookDataModel: BookDataModel) {
        val action = BooksFragmentDirections.actionBooksFragmentToBookDetailsFragment()
        action.bookData = bookDataModel
        view?.let { Navigation.findNavController(it).navigate(action) }
    }
}