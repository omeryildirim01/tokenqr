package com.yildirimomer.tokenqr.ui.paymentlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yildirimomer.tokenqr.databinding.PaymentListItemBinding
import com.yildirimomer.tokenqr.model.entity.PaymentRecord

/**
 * Created by OMER YILDIRIM on 12/5/21.
 * yildirimomer01@gmail.com
 */
class PaymentAdapter(
    private val onItemClicked: (PaymentRecord) -> Unit
) : ListAdapter<PaymentRecord, PaymentAdapter.PaymentViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<PaymentRecord>() {
            override fun areItemsTheSame(oldItem: PaymentRecord, newItem: PaymentRecord): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PaymentRecord, newItem: PaymentRecord): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val viewHolder = PaymentViewHolder(
            PaymentListItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PaymentViewHolder(
        private var binding: PaymentListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(payment: PaymentRecord) {
            binding.itemName.text = "Payment ID: ${payment.id}"
            binding.itemDescription.text = "Payment Amount:  ${payment.paymentAmount}"
            binding.itemDescription2.text = "Pos ID:  ${payment.posID} - Application ID: ${payment.applicationID}"
            binding.itemDescription3.text = "Return Code: ${payment.returnCode} - Return Description: ${payment.returnDesc}"
        }
    }
}