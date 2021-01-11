package ua.ck.zabochen.android.concatadapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.ck.zabochen.android.concatadapter.databinding.AdapterItemEmptyBinding

class EmptyAdapter : ListAdapter<String, EmptyAdapter.EmptyViewHolder>(EmptyItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {
        return EmptyViewHolder(
            AdapterItemEmptyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EmptyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EmptyViewHolder(
        private val binding: AdapterItemEmptyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            binding.tvTitle.text = title
        }
    }

    object EmptyItemCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }
}