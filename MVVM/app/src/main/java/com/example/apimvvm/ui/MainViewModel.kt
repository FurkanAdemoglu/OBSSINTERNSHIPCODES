package com.example.apimvvm.ui

import android.app.Application
import android.provider.SyncStateContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apimvvm.model.Show
import com.example.apimvvm.util.Constants
import com.example.apimvvm.util.Constants.SECOND_SHARED_PREFS_NAME
import com.example.apimvvm.util.ServiceManager
import com.example.apimvvm.util.SingleLiveEvent
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private var sharedPreferences = application.getSharedPreferences(
    Constants.SECOND_SHARED_PREFS_NAME,
        AppCompatActivity.MODE_PRIVATE
    )

    private val showListMutableLiveData = MutableLiveData<List<Show>>()
    val showListLiveData: LiveData<List<Show>> = showListMutableLiveData

    val errorStateLiveData = SingleLiveEvent<String>()

    fun getTvShowsBySearchKey(searchKey: String) {
        viewModelScope.launch {
            try {
                val result = ServiceManager.service.searchShows(searchKey)
                saveSearchKey(searchKey)
                val showList = arrayListOf<Show>()
                for (showResult in result) {
                    showList.add(showResult.show)
                }
                showListMutableLiveData.postValue(showList)
            } catch (e: Exception) {
                errorStateLiveData.postValue("Bir hata oluştu")
                Log.e("hata", "service call error", e)
            }
        }
    }

    private fun saveSearchKey(searchKey: String) {
        sharedPreferences.edit().putString(MainActivity.SEARCH_KEY, searchKey).apply()
    }


}