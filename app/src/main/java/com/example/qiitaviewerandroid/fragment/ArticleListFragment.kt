package com.example.qiitaviewerandroid.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import com.example.qiitaviewerandroid.activity.MainActivity
import com.example.qiitaviewerandroid.R
import com.example.qiitaviewerandroid.databinding.FragmentArticleListBinding
import com.example.qiitaviewerandroid.view.articlelist.ArticleListItemAdapter
import com.example.qiitaviewerandroid.view.articlelist.ArticleListViewModel
import com.example.qiitaviewerandroid.view.common.TagsAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ArticleListFragment : Fragment() {

    private val viewModel: ArticleListViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(ArticleListViewModel::class.java)
    }

    private val itemListener: ArticleListItemAdapter.ArticleListItemListner by lazy {
        ArticleListItemAdapter.ArticleListItemListner {
            val action =
                ArticleListFragmentDirections.actionArticleListFragmentToArticleDetailFragment(it)
            view?.findNavController()?.navigate(action)
        }
    }

    private val tagListener: TagsAdapter.TagButtonItemListener by lazy {
        TagsAdapter.TagButtonItemListener {
            val action =
                ArticleListFragmentDirections.actionArticleListFragmentToTagRelatedArticleListFragment(
                    it
                )
            view?.findNavController()?.navigate(action)
        }
    }


    private val adapter = ArticleListItemAdapter(itemListener, tagListener)

    private var searchJob: Job? = null

    private fun search(query: String? = null) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchArticleList(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticleListBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.articleListSwipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.articleListSwipeRefresh.isRefreshing =
                    loadStates.refresh is LoadState.Loading
            }
        }

        binding.articleListRecyclerview.adapter = adapter

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        search()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.options_menu, menu)

        val searchView =
            SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        searchView.queryHint = getString(R.string.search_hint)
        searchView.isIconified = false
        menu.findItem(R.id.search).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                search(p0)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }
}
