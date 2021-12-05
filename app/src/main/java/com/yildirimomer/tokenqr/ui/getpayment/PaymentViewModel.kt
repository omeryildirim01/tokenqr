package com.yildirimomer.tokenqr.ui.getpayment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yildirimomer.tokenqr.core.NetworkResult
import com.yildirimomer.tokenqr.data.Repository
import com.yildirimomer.tokenqr.model.dto.GetQrResponse
import com.yildirimomer.tokenqr.model.dto.PaymentRequest
import com.yildirimomer.tokenqr.model.dto.PaymentResponse
import com.yildirimomer.tokenqr.util.getDummyPaymentRequest
import com.yildirimomer.tokenqr.util.getPaymentRecord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
@HiltViewModel
class PaymentViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _getQrResponseData = MutableLiveData<GetQrResponse>()
    val getQrResponseLiveData: LiveData<GetQrResponse>
        get() = _getQrResponseData

    private val _getPaymentData = MutableLiveData<NetworkResult<PaymentResponse>>()
    val getPaymentLiveData: LiveData<NetworkResult<PaymentResponse>>
        get() = _getPaymentData

    private val _insertState = MutableLiveData<Unit>()
    val insertStateLiveData: LiveData<Unit>
        get() = _insertState


    fun getPaymentData() = getPaymentLiveData

    fun getQrResponseData() = getQrResponseLiveData

    /**
     * to create payment api call by using qr data
     */
    fun getPaymentApiData(paymentRequest: PaymentRequest) = viewModelScope.launch {
        repository.getPaymentApi(paymentRequest = paymentRequest).collect { result ->
            _getPaymentData.value = result
        }
    }

    fun createMockPaymentRequest(qrData: GetQrResponse) {
        _getQrResponseData.value = qrData
        getPaymentApiData(paymentRequest = qrData.getDummyPaymentRequest())
    }

    fun savePayment(paymentResponse: PaymentResponse) {
        viewModelScope.launch {
            val paymentRecord = paymentResponse.getPaymentRecord(_getQrResponseData.value?.amount)
            repository.insertPayment(paymentRecord)
            _insertState.value = Unit
        }
    }
}