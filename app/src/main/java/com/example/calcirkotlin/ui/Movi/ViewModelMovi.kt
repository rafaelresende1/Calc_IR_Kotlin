package com.example.calc_ir_android.ui.Movi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelMovi : ViewModel() {
    private val mText: MutableLiveData<String?>?
    fun getText(): LiveData<String?>? {
        return mText
    }

    init {
        mText = MutableLiveData()
        mText.setValue("This is Movi fragment")
    }
}