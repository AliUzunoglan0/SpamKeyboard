package com.example.spamkeyboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spamkeyboard.R
import com.example.spamkeyboard.models.SuggestionModel

class SuggestionAdapter(val context : Context?, val suglist: ArrayList<SuggestionModel> = ArrayList<SuggestionModel>()) :
    RecyclerView.Adapter<SuggestionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var suggTitle : TextView = view.findViewById(R.id.sugg_item_title)
//        var suggId : TextView = view.findViewById(R.id.sugg_item_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_spam_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return suglist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsSug = suglist[position]
        holder.suggTitle.text = itemsSug.sugg
//        holder.suggId.text = itemsSug.id.toString()
    }
}