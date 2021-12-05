package com.yildirimomer.tokenqr.data

import com.yildirimomer.tokenqr.core.BaseApiResponse
import com.yildirimomer.tokenqr.core.NetworkResult
import com.yildirimomer.tokenqr.data.local.dao.PaymentDao
import com.yildirimomer.tokenqr.data.remote.RemoteDataSource
import com.yildirimomer.tokenqr.model.dto.GetQrRequest
import com.yildirimomer.tokenqr.model.dto.GetQrResponse
import com.yildirimomer.tokenqr.model.dto.PaymentRequest
import com.yildirimomer.tokenqr.model.dto.PaymentResponse
import com.yildirimomer.tokenqr.model.entity.PaymentRecord
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: PaymentDao
) : BaseApiResponse() {

    suspend fun getQrApi(totalReceiptAmount: GetQrRequest): Flow<NetworkResult<GetQrResponse>> {
        return flow<NetworkResult<GetQrResponse>> {
            emit(safeApiCall { remoteDataSource.getQrApi(totalReceiptAmount) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPaymentApi(paymentRequest: PaymentRequest): Flow<NetworkResult<PaymentResponse>> {
        return flow<NetworkResult<PaymentResponse>> {
            emit(safeApiCall { remoteDataSource.getPaymentApi(paymentRequest) })
        }.flowOn(Dispatchers.IO)
    }

    fun getAllPayments(): Flow<List<PaymentRecord>> {
        return localDataSource.getAll()
    }

    fun deleteAll() {
        localDataSource.deleteAll()
    }

    suspend fun deletePayment(paymentRecord: PaymentRecord) {
        localDataSource.delete(paymentRecord)
    }

    suspend fun updatePayment(paymentRecord: PaymentRecord) {
        localDataSource.update(paymentRecord)
    }

    suspend fun insertPayment(paymentRecord: PaymentRecord) {
        localDataSource.insert(paymentRecord)
    }
}