package com.example.qiitaviewerandroid.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.qiitaviewerandroid.R

class ArticleDetailFragment : Fragment() {

    private val args: ArticleDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
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
