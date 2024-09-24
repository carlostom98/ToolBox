package com.example.cryptocurrency.presenter.recyclerviewres

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocurrency.domain.interfaces.AdapterItems
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter



abstract class SuperListDelegationAdapter: ListDelegationAdapter<List<AdapterItems>>() {
    open fun updateList(newList: List<AdapterItems>) {
        val diffResult = DiffUtil.calculateDiff(ParentDiffUtil(this.items ?: emptyList(), newList))
        this.items = newList
        super.setItems(items)
        diffResult.dispatchUpdatesTo(this)
    }

    private class ParentDiffUtil(
        private val oldList: List<AdapterItems>,
        private val newList: List<AdapterItems>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].isItemTheSame(newList[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].isContentTheSame(newList[newItemPosition])
        }

    }
}
