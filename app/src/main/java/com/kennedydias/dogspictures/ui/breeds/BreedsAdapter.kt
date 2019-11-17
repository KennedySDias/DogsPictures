package com.kennedydias.dogspictures.ui.breeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kennedydias.dogspictures.R
import com.kennedydias.dogspictures.databinding.ListItemBreedBinding
import com.kennedydias.domain.model.BreedData

class BreedsAdapter : RecyclerView.Adapter<BreedsAdapter.ViewHolder>() {

    private val list = mutableListOf<BreedData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_breed,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding?.item = list[position]
        binding?.executePendingBindings()
    }

    fun update(newList: List<BreedData>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ListItemBreedBinding? = DataBindingUtil.bind(itemView)

    }

}