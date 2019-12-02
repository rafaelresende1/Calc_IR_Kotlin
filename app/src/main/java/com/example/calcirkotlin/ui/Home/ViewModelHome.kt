package com.example.calc_ir_android.ui.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelHome : ViewModel() {
    private val mText: MutableLiveData<String?>?
    fun getText(): LiveData<String?>? {
        return mText
    }

    init {
        mText = MutableLiveData()
        mText.setValue("This is home fragment")
    }
}