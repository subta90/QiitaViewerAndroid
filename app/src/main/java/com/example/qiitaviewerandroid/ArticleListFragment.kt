package com.example.qiitaviewerandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import com.example.qiitaviewerandroid.databinding.FragmentArticleListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ArticleListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleListFragment : Fragment() {

    private val dummyData = ArticleOverview.dummyData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentArticleListBinding = inflate(inflater, R.layout.fragment_article_list, container, false)
        val adapter = ArticleAdapter()
        adapter.articles = dummyData
        binding.articleListRecyclerview.adapter = adapter
        return binding.root
    }
}
