package io.github.hadyahmed00.movieapp.Adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.hadyahmed00.movieapp.models.Movie
import io.github.hadyahmed00.movieapp.databinding.MovieCardBinding


class MovieAdapter ( val onClickListener: OnClickListener ) : ListAdapter<Movie, MovieAdapter.MainListViewHolder>(DiffCallback){



    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    class MainListViewHolder(val binding : MovieCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind (item: Movie){
            binding.movie = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        return MainListViewHolder(MovieCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickListener.onClick(getItem(position))
        }
    }

    class OnClickListener(val clickListener: (item:Movie) -> Unit) {
        fun onClick(item:Movie) = clickListener(item)
    }

}