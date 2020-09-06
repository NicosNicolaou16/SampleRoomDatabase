package com.nick.sampleroom.modules.books

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.nick.sampleroom.R
import com.nick.sampleroom.application.SampleRoomApplication
import com.nick.sampleroom.database.BookModel
import com.nick.sampleroom.database.BookTypeModel
import com.nick.sampleroom.modules.books.model.BookDataModel
import com.nick.sampleroom.utils.base_class.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class BooksViewModels(application: Application) : BaseViewModel(application) {

    var bookDataList = createTheData().asLiveData()
    var bookDataListLiveData = MutableLiveData<MutableList<BookDataModel>>()

    companion object {
        private val listOfBookType = mutableListOf("Adventure", "Romance", "Mystery", "", "", "", "", "", "", "", "", "")
    }

    private fun createTheData() =
            flow {
                loading.value = true
                var bookDataModelList = mutableListOf<BookDataModel>()
                withContext(Dispatchers.IO) {
                    val bookModelList = createDummyData()
                    BookModel.insertBooks(bookModelList).collect {
                        bookDataModelList = BookDataModel.createTheBookData(it)
                    }
                }
                loading.value = false
                emit(bookDataModelList)
            }.catch { e ->
                loading.value = false
                error.value = e.message
            }

    fun createTheDataWithLiveData() {
        launch {
            try {
                var bookDataModelList = mutableListOf<BookDataModel>()
                withContext(Dispatchers.IO) {
                    //bookDataModelList = BookDataModel.createTheBookData()
                }
                bookDataListLiveData.value = bookDataModelList
            } catch (e: Exception) {
                error.value = getApplication<SampleRoomApplication>().getString(R.string.something_went_wrong)
            }
        }
    }

    private fun createDummyData(): MutableList<BookModel> {
        val bookTypeModelList = mutableListOf<BookTypeModel>()
        val bookModelList = mutableListOf<BookModel>()
        for (i in 0 until 11) {
            val pages = getRandomBookPages(500, 999)
            val bookTypeModel = BookTypeModel(i.toLong(), listOfBookType[i])
            bookTypeModelList.add(bookTypeModel)
            bookModelList.add(BookModel(i.toLong(), "Book $i", pages, bookTypeModel, -1L))
        }
        return bookModelList
    }

    private fun getRandomBookPages(min: Long, max: Long): Long {
        return Random(System.currentTimeMillis()).nextLong(min, max)
    }
}