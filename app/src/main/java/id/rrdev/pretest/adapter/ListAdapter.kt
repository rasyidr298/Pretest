package id.rrdev.pretest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.rrdev.pretest.data.Data
import id.rrdev.pretest.databinding.ItemDataBinding

class ListAdapter() : RecyclerView.Adapter<ListAdapter.EventHolder>() {

    private val list = mutableListOf<Data>()

    fun addList(listData : List<Data>){
        val diffResult : DiffUtil.DiffResult = DiffUtil.calculateDiff(
            EventDiffCallback(
                list,
                listData
            )
        )
        list.clear()
        list.addAll(listData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventHolder(binding)
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class EventHolder(private val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data) {
            with(binding){
                tvName.text = data.name
            }
        }
    }

    class EventDiffCallback(
        private var oldList : List<Data>,
        private var newList : List<Data>
    ) : DiffUtil.Callback(){
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldList[oldItemPosition].id == newList[newItemPosition].id)
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}