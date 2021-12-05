package com.yildirimomer.tokenqr.ui.paymentlist

import androidx.lifecycle.ViewModel
import com.yildirimomer.tokenqr.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
@HiltViewModel
class PaymentListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

}