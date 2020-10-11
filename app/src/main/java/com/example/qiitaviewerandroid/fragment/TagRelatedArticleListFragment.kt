package com.example.qiitaviewerandroid.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.qiitaviewerandroid.R
import com.example.qiitaviewerandroid.view.articlelist.ArticleListItemAdapter
import com.example.qiitaviewerandroid.view.tagrelatedarticlelist.TagRelatedArticleListViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TagRelatedArticleListFragment : Fragment() {

    private val viewModel: TagRelatedArticleListViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(TagRelatedArticleListViewModel::class.java)
    }


    private var searchJob: Job? = null

    private fun search(tagID: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchTagRelatedArticleList(tagID).collectLatest {
                //adapter.submitData(it)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tag_related_article_list, container, false)
    }

}
