package id.rrdev.pretest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.rrdev.pretest.data.response.DataProduct
import id.rrdev.pretest.databinding.ItemProductBinding

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.EventHolder>() {

    private val list = mutableListOf<DataProduct>()

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
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventHolder(binding)
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class EventHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataProduct) {
            with(binding){
                tvName.text = data.nama
                tvPrice.text = "Rp."+data.harga
            }
        }
    }

}