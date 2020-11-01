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
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

    private val adapter: TagRelatedArticleListItemAdapter by lazy {
        TagRelatedArticleListItemAdapter(args.tag, itemListener, tagListener)
    }

    private var searchJob: Job? = null

    private fun search(tagID: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchTagRelatedArticleList(tagID).collectLatest {
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

        binding.tagRelatedArticleListSwipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }

        binding.tagRelatedArticleListRecyclerview.adapter = adapter

        search(args.tag.name)

        return binding.root
    }

}
