package com.yildirimomer.tokenqr.ui.getqr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.yildirimomer.tokenqr.R
import com.yildirimomer.tokenqr.core.BaseFragment
import com.yildirimomer.tokenqr.core.NetworkResult
import com.yildirimomer.tokenqr.databinding.GetQrFragmentBinding
import com.yildirimomer.tokenqr.util.getQrCodeImage
import com.yildirimomer.tokenqr.util.hasInternetConnection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

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
        checkArgs()
    }

    override fun setUI() {
        binding = GetQrFragmentBinding.inflate(layoutInflater)
        binding.btnProceed.setOnClickListener {
            viewModel.getQrResponseaLiveData.value?.data?.let { qrData ->
                qrData.amount = viewModel.paymentAmount
                val directions =
                    GetQrFragmentDirections.actionGetQrFragmentToPaymentFragment(qrData = qrData)
                binding.root.findNavController().navigate(directions)
            }
        }
    }

    override fun observeData() {
        viewModel.getQrResponseaLiveData.observe(this, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response.data?.QRdata?.let { qrData ->
                        showToastMessage(getString(R.string.qr_code_created_successfully))
                        binding.ivQr.setImageBitmap(qrData.getQrCodeImage())
                        binding.btnProceed.visibility = View.VISIBLE
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

    private fun checkArgs() {
        val bundle = arguments ?: return
        viewModel.paymentAmount = GetQrFragmentArgs.fromBundle(bundle).paymentAmount
        if (this.hasInternetConnection()) {
            viewModel.getQrApiData()
        } else {
            showToastMessage(getString(R.string.please_check_your_internet_connection))
        }
    }
}