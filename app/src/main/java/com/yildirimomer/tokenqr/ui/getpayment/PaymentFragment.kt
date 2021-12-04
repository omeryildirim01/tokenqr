package com.yildirimomer.tokenqr.ui.getpayment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.yildirimomer.tokenqr.R
import com.yildirimomer.tokenqr.core.BaseFragment
import com.yildirimomer.tokenqr.databinding.PaymentFragmentBinding
import com.yildirimomer.tokenqr.databinding.PaymentListFragmentBinding
import com.yildirimomer.tokenqr.ui.paymentlist.PaymentListViewModel
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
    }

    override fun setUI() {
        binding = PaymentFragmentBinding.inflate(layoutInflater)
    }

    override fun observeData() {

    }

    override fun provideLayoutResId()  = R.layout.payment_fragment

    override fun provideBinding() = binding

    override fun provideViewModel() = viewModel
}