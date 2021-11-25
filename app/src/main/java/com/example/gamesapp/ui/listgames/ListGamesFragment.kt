package com.example.gamesapp.ui.listgames

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gamesapp.databinding.FragmentListGamesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListGamesFragment : Fragment() {
    private var _binding: FragmentListGamesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListGamesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListGamesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            val decoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)

            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }

        getGamesList("")

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                getGamesList(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    fun getGamesList(search: String) {
        lifecycleScope.launch {
            viewModel.getGames(search).collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }
}