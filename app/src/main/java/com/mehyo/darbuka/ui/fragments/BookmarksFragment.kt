package com.mehyo.darbuka.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehyo.darbuka.databinding.FragmentBookmarksBinding
import com.mehyo.darbuka.ui.adapters.OnItemClickListener
import com.mehyo.darbuka.ui.adapters.ReposAdapter
import com.mehyo.darbuka.ui.viewmodels.MyViewModel
import com.mehyo.darbuka.util.Resource
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BookmarksFragment : Fragment(), OnItemClickListener {
    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!
    private val myViewModel: MyViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reposAdapter = ReposAdapter(this@BookmarksFragment)

        binding.apply {
            rvBook.apply {
                adapter = reposAdapter
                layoutManager = LinearLayoutManager(context)
            }

            myViewModel.bookedRepos.observe(viewLifecycleOwner, { result ->
                reposAdapter.submitList(result.data)
                pbBook.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.message
            })
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onItemClicked(id: Int) {
        findNavController().navigate(BookmarksFragmentDirections.actionBookmarksFragmentToDetailsFragment(id))
    }
}