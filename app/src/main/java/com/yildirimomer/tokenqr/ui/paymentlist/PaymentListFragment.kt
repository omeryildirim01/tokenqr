package com.yildirimomer.tokenqr.ui.paymentlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.yildirimomer.tokenqr.R
import com.yildirimomer.tokenqr.core.BaseFragment
import com.yildirimomer.tokenqr.databinding.PaymentListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
@InternalCoroutinesApi
@AndroidEntryPoint
class PaymentListFragment : BaseFragment(R.layout.payment_list_fragment) {
    private val viewModel by viewModels<PaymentListViewModel>()
    private lateinit var binding: PaymentListFragmentBinding

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
        binding = PaymentListFragmentBinding.inflate(layoutInflater)
        binding.btnNewPayment.setOnClickListener {
           try {
               val paymentAmount: Int = binding.txtNewPaymentAmount.text.toString().toInt()
               if (paymentAmount > 0) {
                   val directions =
                       PaymentListFragmentDirections.actionPaymentListFragment2ToGetQrFragment(paymentAmount = paymentAmount)
                   binding.root.findNavController().navigate(directions)
               }
           }catch (e: Exception){
               showToastMessage(e.message.toString())
           }
        }
        binding.progressBar.visibility =  View.GONE
    }

    override fun observeData() {
    }

    override fun provideLayoutResId() = R.layout.payment_list_fragment

    override fun provideBinding() = binding

    override fun provideViewModel() = viewModel
}