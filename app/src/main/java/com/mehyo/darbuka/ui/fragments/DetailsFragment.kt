package com.mehyo.darbuka.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.mehyo.darbuka.databinding.FragmentDetailsBinding
import com.mehyo.darbuka.model.SquareRepos
import com.mehyo.darbuka.ui.viewmodels.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()
    private val myViewModel: MyViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            val repo=myViewModel.getRepoByID(args.id)

            withContext(Dispatchers.Main){
                binding.apply {
                    tvRepoName.text=repo.name
                    tvRepoStars.text=repo.stargazers_count.toString()
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val repo=myViewModel.getRepoByID(args.id)
                val updatedSquareRepos= SquareRepos(repo.id,repo.name,repo.stargazers_count,true)
                myViewModel.updateRepo(updatedSquareRepos)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}