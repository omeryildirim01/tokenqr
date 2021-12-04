package com.yildirimomer.tokenqr.core

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
abstract class BaseFragment constructor(@LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId) {

    abstract fun setUI()

    abstract fun observeData()

    abstract fun provideLayoutResId(): Int

    abstract fun provideBinding(): ViewBinding

    abstract fun provideViewModel(): ViewModel

    fun showToastMessage(message: String) {
        Toast.makeText(
            activity,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}