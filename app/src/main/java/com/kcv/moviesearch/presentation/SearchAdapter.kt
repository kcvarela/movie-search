package com.kcv.moviesearch.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kcv.moviesearch.databinding.ItemSearchBinding
import com.kcv.moviesearch.domain.Search

class SearchAdapter(private val onClickListener: OnClickListener) : ListAdapter<Search,
        SearchAdapter.SearchViewHolder>(DiffCallback) {

    class SearchViewHolder(private var binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(searchNew: Search) {
            binding.itemList = searchNew
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val searchNew = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(searchNew)
        }
        holder.bind(searchNew)

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }
    }

    class OnClickListener(val clickListener: (itemSearched: Search) -> Unit) {
        fun onClick(itemSearched: Search) = clickListener(itemSearched)
    }
}