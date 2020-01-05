package com.example.qiitaviewerandroid

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){

    class CustomViewHolder(val layout: RelativeLayout) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.article_list_widget, parent, false) as RelativeLayout
        return CustomViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        var textView = holder.layout.findViewById<TextView>(R.id.appwidget_text)
        textView.text = dataSet[position]
    }

    override fun getItemCount() = dataSet.size

}