package com.yildirimomer.tokenqr.core

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
abstract class BaseActivity constructor(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {
    override fun onBackPressed() {
        finish()
    }
}