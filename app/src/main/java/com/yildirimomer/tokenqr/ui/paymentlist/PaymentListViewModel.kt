package com.yildirimomer.tokenqr.ui.paymentlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yildirimomer.tokenqr.data.Repository
import com.yildirimomer.tokenqr.model.entity.PaymentRecord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
@HiltViewModel
class PaymentListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _PaymentRecords = MutableLiveData<List<PaymentRecord>?>()
    val paymentRecordsLiveData: LiveData<List<PaymentRecord>?>
        get() = _PaymentRecords

    fun getPaymentRecords() = paymentRecordsLiveData

    fun loadPaymentRecords() {
        viewModelScope.launch {
            val response = repository.getAllPayments()
            response.apply {
                this.collect { items ->
                    _PaymentRecords.value = items
                }
            }
        }
    }
}