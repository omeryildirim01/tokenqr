package com.yildirimomer.tokenqr.ui.getpayment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.yildirimomer.tokenqr.R
import com.yildirimomer.tokenqr.core.BaseFragment
import com.yildirimomer.tokenqr.core.NetworkResult
import com.yildirimomer.tokenqr.databinding.PaymentFragmentBinding
import com.yildirimomer.tokenqr.util.hasInternetConnection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
@InternalCoroutinesApi
@AndroidEntryPoint
class PaymentFragment : BaseFragment(R.layout.payment_fragment) {
    private val viewModel by viewModels<PaymentViewModel>()
    private lateinit var binding: PaymentFragmentBinding

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
        binding = PaymentFragmentBinding.inflate(layoutInflater)
        binding.btnHome.visibility = View.GONE
        binding.btnHome.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_paymentFragment_to_paymentListFragment2)
        }
    }

    override fun observeData() {
        viewModel.insertStateLiveData.observe(this, {
            showToastMessage(getString(R.string.payment_saved_successfully))
        })

        viewModel.getPaymentLiveData.observe(this, { response ->
            binding.btnHome.visibility = View.VISIBLE
            when (response) {
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response.data?.let { paymentResponse ->
                        binding.ivStatus.setImageResource(R.drawable.ic_baseline_done_outline_24)
                        binding.txtDetails1.text = "Result:  Payment process finished successfully."
                        binding.txtDetails2.text =
                            "POS ID:  ${paymentResponse.posID} - Return Code: ${paymentResponse.returnCode} "
                        binding.txtDetails3.text =
                            "Application ID:  ${paymentResponse.applicationID} - Return Description: ${paymentResponse.returnDesc}"
                        binding.txtDetails4.text = "Session ID:  ${paymentResponse.sessionID}"
                        viewModel.savePayment(paymentResponse = paymentResponse)
                    }
                }
                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    response.message?.let {
                        showToastMessage(it)
                        binding.txtDetails1.text =
                            "Result:  Payment process failed. Please try again."
                    }
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun provideLayoutResId() = R.layout.payment_fragment

    override fun provideBinding() = binding

    override fun provideViewModel() = viewModel

    private fun checkArgs() {
        val bundle = arguments ?: return
        val qrData = PaymentFragmentArgs.fromBundle(bundle).qrData
        if (this.hasInternetConnection()) {
            viewModel.createMockPaymentRequest(qrData)
        } else {
            showToastMessage(getString(R.string.please_check_your_internet_connection))
        }
    }
}