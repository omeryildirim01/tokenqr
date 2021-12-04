package com.yildirimomer.tokenqr.ui.getqr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yildirimomer.tokenqr.core.NetworkResult
import com.yildirimomer.tokenqr.data.Repository
import com.yildirimomer.tokenqr.model.GetQrRequest
import com.yildirimomer.tokenqr.model.GetQrResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
@HiltViewModel
class GetQrViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    private val _getQrResponseData = MutableLiveData<NetworkResult<GetQrResponse>>()
    val getQrResponseaLiveData: LiveData<NetworkResult<GetQrResponse>>
        get() = _getQrResponseData

    /**
     * to create api call by using total amount of payment
     */
    fun getQrApiData(totalAmount: GetQrRequest) = viewModelScope.launch {
       repository.getQrApi(totalReceiptAmount = totalAmount).collect { result ->
           _getQrResponseData.value = result
       }
    }
}