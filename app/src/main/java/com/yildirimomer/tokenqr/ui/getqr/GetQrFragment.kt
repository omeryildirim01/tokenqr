package com.yildirimomer.tokenqr.ui.getqr

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.yildirimomer.tokenqr.R
import com.yildirimomer.tokenqr.core.BaseFragment
import com.yildirimomer.tokenqr.core.NetworkResult
import com.yildirimomer.tokenqr.databinding.GetQrFragmentBinding
import com.yildirimomer.tokenqr.model.GetQrRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import java.lang.Exception

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
@InternalCoroutinesApi
@AndroidEntryPoint
class GetQrFragment : BaseFragment(R.layout.get_qr_fragment) {
    private val viewModel by viewModels<GetQrViewModel>()
    private lateinit var binding: GetQrFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUI()
        observeData()
        viewModel.getQrApiData(GetQrRequest(totalReceiptAmount = 100))
    }

    override fun setUI() {
        binding = GetQrFragmentBinding.inflate(layoutInflater)
    }

    override fun observeData() {
        viewModel.getQrResponseaLiveData.observe(this, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response.data?.QRdata?.let { qrData ->
                        val qrImage = getQrCodeBitmap(qrData)
                        binding.ivQr.setImageBitmap(qrImage)
                    }
                }
                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    response.message?.let {
                        showToastMessage(it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun provideLayoutResId() = R.layout.get_qr_fragment

    override fun provideBinding() = binding

    override fun provideViewModel() = viewModel

    fun getQrCodeBitmap(content: String): Bitmap? {
        try {
            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            return bitmap
        }catch (e: Exception){
            return null
        }
    }
}