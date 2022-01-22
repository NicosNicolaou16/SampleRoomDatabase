package com.nick.sampleroom.modules.books

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.nick.sampleroom.database.models.BookModel
import com.nick.sampleroom.database.models.BookGenreModel
import com.nick.sampleroom.modules.books.model.BookDataModel
import com.nick.sampleroom.utils.base_classes.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class BooksViewModels(application: Application) : BaseViewModel(application) {

    var bookDataList = createTheDataWithLiveDataKTX().asLiveData()
    var bookDataListLiveData = MutableLiveData<MutableList<BookDataModel>>()

    companion object {
        private const val DELAY_LOADING = 1500L
        private val listOfBookGenre = mutableListOf(
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
            delay(DELAY_LOADING)
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
            loading.value = true
            try {
                var bookDataModelList = mutableListOf<BookDataModel>()
                delay(DELAY_LOADING)
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
                error.value = e.message
            }
        }
    }

    private fun createDummyData(): MutableList<BookModel> {
        val bookModelList = mutableListOf<BookModel>()
        for (i in 0 until 11) {
            val pages = getRandomBookPages(500, 999)
            val bookTypeModel = BookGenreModel(i.toLong(), listOfBookGenre[i])
            bookModelList.add(BookModel(i.toLong(), "Book $i", pages, bookTypeModel, i.toLong()))
        }
        return bookModelList
    }

    private fun getRandomBookPages(min: Long, max: Long): Long {
        return Random(System.currentTimeMillis()).nextLong(min, max)
    }
}