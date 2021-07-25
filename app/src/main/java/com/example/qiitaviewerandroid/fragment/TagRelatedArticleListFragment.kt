package com.example.qiitaviewerandroid.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.qiitaviewerandroid.databinding.FragmentTagRelatedArticleListBinding
import com.example.qiitaviewerandroid.view.articlelist.ArticleListItemAdapter
import com.example.qiitaviewerandroid.view.common.TagsAdapter
import com.example.qiitaviewerandroid.view.tagrelatedarticlelist.TagRelatedArticleListItemAdapter
import com.example.qiitaviewerandroid.view.tagrelatedarticlelist.TagRelatedArticleListViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

class TagRelatedArticleListFragment : Fragment() {

    private val args: TagRelatedArticleListFragmentArgs by navArgs()

    private val viewModel: TagRelatedArticleListViewModel by lazy {
        val factory = TagRelatedArticleListViewModel.Factory(args.tag.name)
        ViewModelProvider(this, factory)[TagRelatedArticleListViewModel::class.java]
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

    private lateinit var adapter: TagRelatedArticleListItemAdapter

    private lateinit var searchJob: Job

    private suspend fun searchTagDetail() {
        val tagDetail = viewModel.searchTagDetail()
        adapter = TagRelatedArticleListItemAdapter(tagDetail, itemListener, tagListener)
    }

    private suspend fun search() {
        lifecycleScope.launch {
            viewModel.searchTagRelatedArticleList().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTagRelatedArticleListBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        searchJob = lifecycleScope.launch {
            // Tagの詳細を取得しないとAdapterが初期化できないため同一CoroutineScope内で処理する
            searchTagDetail()
            search()
            binding.tagRelatedArticleListRecyclerview.adapter = adapter
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.tagRelatedArticleListSwipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }

        binding.tagRelatedArticleListSwipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
        return binding.root
    }

}
