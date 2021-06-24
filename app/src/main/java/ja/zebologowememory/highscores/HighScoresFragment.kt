package ja.zebologowememory.highscores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import ja.zebologowememory.R
import ja.zebologowememory.data.Repository
import ja.zebologowememory.data.Score
import ja.zebologowememory.databinding.HighScoresFragmentBinding
import ja.zebologowememory.highscores.adapters.ScoreAdapter
import ja.zebologowememory.highscores.adapters.ScoreClickListener

class HighScoresFragment : Fragment(), ScoreClickListener {

    companion object {
        fun newInstance() = HighScoresFragment()
    }

    lateinit var mAdapter : ScoreAdapter
    private var binding : HighScoresFragmentBinding? = null
    private val highScoresFragmentViewModel: HighScoresViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<HighScoresFragmentBinding>(inflater, R.layout.high_scores_fragment, container, false)
        this.binding = binding
        mAdapter = ScoreAdapter(this)
        mAdapter.submitList(Repository.getAllScores().value)
        binding.recyclerView.adapter = mAdapter

        subscribeUi(mAdapter, binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            highScoresViewModel = highScoresFragmentViewModel
            highScoresFragment = this@HighScoresFragment
            // TODO: Use the ViewModel
        }
    }

    private fun subscribeUi(newAdapter: ScoreAdapter, binding: HighScoresFragmentBinding) {
        binding.recyclerView.adapter = newAdapter
        highScoresFragmentViewModel.scores.observe(viewLifecycleOwner) { result ->
            newAdapter.submitList(result.sortedByDescending { it.points.toInt() })
            newAdapter.notifyDataSetChanged()
        }
    }

    override fun chooseScore(score: Score) {
        val points = "${score.points}"
        val date = "${score.date}"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, "Punkty: " + points +  " " + "Data: " + date, duration)
        toast.show()
    }

    fun back() {
        findNavController().navigate(R.id.action_highScoresFragment_to_menuFragment)
    }

    fun deleteScores() {
        Repository.removeScores()
        val toast = Toast.makeText(activity, "Usunięto wyniki z tabeli wyników", Toast.LENGTH_LONG)
        toast.show()
        findNavController().navigate(R.id.action_highScoresFragment_to_menuFragment)
    }
}