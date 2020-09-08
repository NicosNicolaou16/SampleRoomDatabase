package com.nick.sampleroom.modules.books

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.nick.sampleroom.R
import com.nick.sampleroom.application.SampleRoomApplication
import com.nick.sampleroom.database.models.BookModel
import com.nick.sampleroom.database.models.BookTypeModel
import com.nick.sampleroom.modules.books.model.BookDataModel
import com.nick.sampleroom.utils.base_class.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class BooksViewModels(application: Application) : BaseViewModel(application) {

    var bookDataList = createTheDataWithLiveDataKTX().asLiveData()
    var bookDataListLiveData = MutableLiveData<MutableList<BookDataModel>>()

    companion object {
        private const val DELAY_LOADING = 1500L
        private val listOfBookType = mutableListOf(
                "Adventure",
                "Romance",
                "Mystery",
                "Horror",
                "Fantasy",
                "Science Fiction",
                "CookBooks",
                "History",
                "Classics",
                "Short Stories",
                "Biographies",
                "Poetry"
        )
    }

    private fun createTheDataWithLiveDataKTX() =
            flow {
                loading.value = true
                var bookDataModelList = mutableListOf<BookDataModel>()
                withContext(Dispatchers.IO) {
                    delay(DELAY_LOADING)
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
            loading.value = true
            try {
                var bookDataModelList = mutableListOf<BookDataModel>()
                withContext(Dispatchers.IO) {
                    val bookModelList = createDummyData()
                    BookModel.insertBooks(bookModelList).collect {
                        bookDataModelList = BookDataModel.createTheBookData(it)
                    }
                }
                loading.value = false
                bookDataListLiveData.value = bookDataModelList
            } catch (e: Exception) {
                loading.value = false
                error.value = getApplication<SampleRoomApplication>().getString(R.string.something_went_wrong)
            }
        }
    }

    private fun createDummyData(): MutableList<BookModel> {
        val bookModelList = mutableListOf<BookModel>()
        for (i in 0 until 11) {
            val pages = getRandomBookPages(500, 999)
            val bookTypeModel = BookTypeModel(i.toLong(), listOfBookType[i])
            bookModelList.add(BookModel(i.toLong(), "Book $i", pages, bookTypeModel, i.toLong()))
        }
        return bookModelList
    }

    private fun getRandomBookPages(min: Long, max: Long): Long {
        return Random(System.currentTimeMillis()).nextLong(min, max)
    }
}