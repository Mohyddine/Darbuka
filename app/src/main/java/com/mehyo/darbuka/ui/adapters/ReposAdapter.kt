package com.mehyo.darbuka.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mehyo.darbuka.model.SquareRepos
import com.mehyo.darbuka.databinding.RepoRowBinding

class ReposAdapter(private val itemClickListener: OnItemClickListener): ListAdapter<SquareRepos, ReposAdapter.ReposViewHolder>(ReposComparator()) {

    class ReposViewHolder(private val binding: RepoRowBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:SquareRepos,clickListener: OnItemClickListener){
            binding.apply {
                tvRepoName.text=item.name
                tvRepoStars.text=item.stargazers_count.toString()
                cvRepo.setOnClickListener {
                    clickListener.onItemClicked(item.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val binding=RepoRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem!=null){
            holder.bind(currentItem,itemClickListener)
        }
    }

    class ReposComparator: DiffUtil.ItemCallback<SquareRepos>(){
        override fun areItemsTheSame(oldItem: SquareRepos, newItem: SquareRepos) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SquareRepos, newItem: SquareRepos) =
            oldItem == newItem

    }
}

interface OnItemClickListener{
    fun onItemClicked(id:Int)
}