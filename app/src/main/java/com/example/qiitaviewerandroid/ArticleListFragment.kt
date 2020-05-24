package com.example.qiitaviewerandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.qiitaviewerandroid.databinding.FragmentArticleListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ArticleListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleListFragment : Fragment() {

    private val viewModel: ArticleListViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(ArticleListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticleListBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it) {
                ArticleListLoadingState.COMPLETE -> {
                    binding.articleListSwipeRefresh.isRefreshing = false
                }
            }
        })

        binding.viewModel = viewModel

        val adapter = ArticleListItemAdapter()
        binding.articleListRecyclerview.adapter = adapter

        binding.articleListSwipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        return binding.root
    }
}
