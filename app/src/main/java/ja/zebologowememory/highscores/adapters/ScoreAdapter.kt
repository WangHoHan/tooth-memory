package ja.zebologowememory.highscores.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ja.zebologowememory.R
import ja.zebologowememory.data.Score
import ja.zebologowememory.databinding.ScoreItemBinding

class ScoreAdapter internal constructor(
    private val mListener: ScoreClickListener
): ListAdapter<Score, ScoreAdapter.ScoreViewHolder>(ScoreDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        return ScoreViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val score = getItem(position)
        holder.bind(score, mListener)
    }

    override fun getItemCount(): Int {
        val result = super.getItemCount()
        return result
    }

    class ScoreViewHolder(val binding: ScoreItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentScore: Score, listener: ScoreClickListener) {
            binding.score = currentScore
            binding.clickListener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ScoreViewHolder {
                val layoutInflater = LayoutInflater.from((parent.context))
                val binding: ScoreItemBinding = DataBindingUtil.inflate(
                    layoutInflater, R.layout.score_item, parent, false)
                return ScoreViewHolder(binding)
            }
        }
    }
}

interface ScoreClickListener {
    fun chooseScore(score : Score)
}

private class ScoreDiffCallback : DiffUtil.ItemCallback<Score>() {
    override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
        return oldItem.points == newItem.points
    }

    override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
        return  oldItem == newItem
    }
}
