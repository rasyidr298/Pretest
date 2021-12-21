package id.rrdev.pretest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.rrdev.pretest.data.response.DataProduct
import id.rrdev.pretest.databinding.ItemTransactionBinding
import id.rrdev.pretest.utils.OnItemClicked

class TransactionAdapter(private val onItemClicked: OnItemClicked) : RecyclerView.Adapter<TransactionAdapter.EventHolder>() {

    val list = mutableListOf<DataProduct>()

    fun addList(listData : List<DataProduct>){
        val diffResult : DiffUtil.DiffResult = DiffUtil.calculateDiff(
            DiffCallback(
                list,
                listData
            )
        )
        list.clear()
        list.addAll(listData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventHolder(binding)
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind(onItemClicked, list[position])
    }

    override fun getItemCount(): Int = list.size

    class EventHolder(private val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(onItemClicked: OnItemClicked, data: DataProduct) {
            with(binding){
                tvName.text = data.nama
                tvPrice.text = "Rp."+data.harga

                var total = 0
                tvTotal.text = "0"

                btnIncrease.setOnClickListener {
                    total += 1
                    onItemClicked.onEventClick(total)
                    tvTotal.text = total.toString()
                }

                btnDecrease.setOnClickListener {
                    if (total != 0) {
                        total -= 1
                        onItemClicked.onEventClick(total)
                        tvTotal.text = total.toString()
                    }
                }
            }
        }
    }
}