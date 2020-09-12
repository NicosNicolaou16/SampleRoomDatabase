package com.nick.sampleroom.modules.books.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nick.sampleroom.R
import com.nick.sampleroom.databinding.BookGenreLayoutRecyclerViewBinding
import com.nick.sampleroom.databinding.BookInfoLayoutRecyclerViewBinding
import com.nick.sampleroom.modules.books.model.BookDataModel

class BookAdapter(private var bookDataModelList: MutableList<BookDataModel>, private var bookListener: BookListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface BookListener {
        fun onClickBookListener(bookDataModel: BookDataModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BookDataModel.BookViewType.BOOK_INFO.ordinal -> BookInfoViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.book_info_layout_recycler_view, parent, false))
            else -> BookTypeViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.book_genre_layout_recycler_view, parent, false))
        }
    }

    fun loadData(bookDataModelList: MutableList<BookDataModel>) {
        this.bookDataModelList.clear()
        this.bookDataModelList.addAll(bookDataModelList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookTypeViewHolder -> holder.bindData(bookDataModelList[position])
            is BookInfoViewHolder -> holder.bindData(bookDataModelList[position])
        }
    }

    override fun getItemViewType(position: Int) = this.bookDataModelList[position].bookViewType.ordinal

    override fun getItemCount() = this.bookDataModelList.size

    private inner class BookTypeViewHolder(var view: BookGenreLayoutRecyclerViewBinding) : RecyclerView.ViewHolder(view.root) {

        fun bindData(bookDataModel: BookDataModel?) = with(view) {
            bookGenre = bookDataModel
        }
    }

    private inner class BookInfoViewHolder(var view: BookInfoLayoutRecyclerViewBinding) : RecyclerView.ViewHolder(view.root), BookListener {

        fun bindData(bookDataModel: BookDataModel?) = with(view) {
            books = bookDataModel
            listener = this@BookInfoViewHolder
        }

        override fun onClickBookListener(bookDataModel: BookDataModel) {
            bookListener.onClickBookListener(bookDataModel)
        }
    }
}