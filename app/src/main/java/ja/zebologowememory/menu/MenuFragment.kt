package ja.zebologowememory.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ja.zebologowememory.R
import ja.zebologowememory.databinding.MenuFragmentBinding

class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private var binding : MenuFragmentBinding? = null
    private val menuFragmentViewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val menuFragmentBinding = MenuFragmentBinding.inflate(inflater, container, false)
        binding = menuFragmentBinding
        return menuFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            menuViewModel = menuFragmentViewModel
            menuFragment = this@MenuFragment
            // TODO: Use the ViewModel
        }
    }

    fun play() {
        findNavController().navigate(R.id.action_menuFragment_to_gameActivity)
    }

    fun highScoresTable() {
        findNavController().navigate(R.id.action_menuFragment_to_highScoresFragment)
    }

    fun exit() {
        System.exit(0);
    }
}