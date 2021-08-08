package com.example.qiitaviewerandroid.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.qiitaviewerandroid.R
import com.example.qiitaviewerandroid.databinding.FragmentArticleDetailBinding
import com.example.qiitaviewerandroid.view.articledetail.ArticleDetailViewModel
import com.example.qiitaviewerandroid.view.common.TagsAdapter

class ArticleDetailFragment : Fragment() {

    private val args: ArticleDetailFragmentArgs by navArgs()

    private val viewModel: ArticleDetailViewModel by lazy {
        val factory = ArticleDetailViewModel.Factory(args.articleOverview.id)
        ViewModelProvider(this, factory)[ArticleDetailViewModel::class.java]
    }

    private val tagListener = TagsAdapter.TagButtonItemListener {
        val action = ArticleDetailFragmentDirections.actionArticleDetailFragmentToTagRelatedArticleListFragment(it)
        view?.findNavController()?.navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticleDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        val tagsAdapter = TagsAdapter(tagListener)
        binding.articleDetailTagsView.adapter = tagsAdapter
        tagsAdapter.submitList(args.articleOverview.tags)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleView = view.findViewById<TextView>(R.id.article_detail_title_view)
        titleView.text = args.articleOverview.title

        val authorView = view.findViewById<TextView>(R.id.article_detail_author_view)
        authorView.text = args.articleOverview.user.id

        val dateView = view.findViewById<TextView>(R.id.article_detail_date_view)
        dateView.text = args.articleOverview.createdAt

    }
}
