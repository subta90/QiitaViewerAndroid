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
import com.example.qiitaviewerandroid.databinding.FragmentTagRelatedArticleListBinding
import com.example.qiitaviewerandroid.view.articlelist.ArticleListItemAdapter
import com.example.qiitaviewerandroid.view.common.TagsAdapter
import com.example.qiitaviewerandroid.view.tagrelatedarticlelist.TagRelatedArticleListItemAdapter
import com.example.qiitaviewerandroid.view.tagrelatedarticlelist.TagRelatedArticleListViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

class TagRelatedArticleListFragment : Fragment() {

    private val args: TagRelatedArticleListFragmentArgs by navArgs()

    private val viewModel: TagRelatedArticleListViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(TagRelatedArticleListViewModel::class.java)
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

    private var adapter: TagRelatedArticleListItemAdapter? = null

    private var searchJob: Job? = null

    private suspend fun searchTagDetail(tagID: String) {
        withContext(Dispatchers.Main) {
            val tagDetail = viewModel.searchTagDetail(tagID)
            adapter = TagRelatedArticleListItemAdapter(tagDetail, itemListener, tagListener)
        }
    }

    private suspend fun search(tagID: String) {
        lifecycleScope.launch {
            viewModel.searchTagRelatedArticleList(tagID).collectLatest {
                adapter?.submitData(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTagRelatedArticleListBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            searchTagDetail(args.tag.name)
            binding.tagRelatedArticleListSwipeRefresh.setOnRefreshListener {
                adapter?.refresh()
            }

            binding.tagRelatedArticleListRecyclerview.adapter = adapter
            search(args.tag.name)
        }

        return binding.root
    }

}
