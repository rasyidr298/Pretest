package id.rrdev.pretest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.rrdev.pretest.data.response.DataProduct
import id.rrdev.pretest.databinding.ItemProductBinding
import id.rrdev.pretest.utils.OnItemClicked
import java.util.*
import kotlin.collections.ArrayList

class ProductAdapter(private val onItemClicked: OnItemClicked) : RecyclerView.Adapter<ProductAdapter.EventHolder>() {

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
        holder.bind(onItemClicked, list[position])
    }

    override fun getItemCount(): Int = list.size

    class EventHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(onItemClicked: OnItemClicked, data: DataProduct) {
            with(binding){
                tvName.text = data.nama
                tvPrice.text = "Rp."+data.harga
                btnEdit.setOnClickListener {
                    onItemClicked.onUpdateProductClick(data)
                }
                btnDelete.setOnClickListener {
                    onItemClicked.onDeleteProductClick(data)
                }
            }
        }
    }

    //search
    fun search(view: SearchView, observeData: ()-> Unit) {
        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                observeData.invoke()

                newText?.lowercase(Locale.getDefault())
                val dataFilter = ArrayList<DataProduct>()

                for (data in this@ProductAdapter.list) {
                    val nama = data.nama!!.lowercase(Locale.getDefault())
                    if (nama.contains(newText!!,true)) {
                        dataFilter.add(data)
                    }
                }
                this@ProductAdapter.addList(dataFilter)
                return false
            }
        })
    }

}