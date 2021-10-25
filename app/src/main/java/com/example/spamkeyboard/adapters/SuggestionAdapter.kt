package com.example.spamkeyboard.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spamkeyboard.R
import com.example.spamkeyboard.databinding.AddSuggestionsPopupBinding
import com.example.spamkeyboard.methods.SQLiteHelperMethods
import com.example.spamkeyboard.models.SuggestionModel

class SuggestionAdapter(
    val context: Context?,
    val suglist: ArrayList<SuggestionModel> = ArrayList()
) :
    RecyclerView.Adapter<SuggestionAdapter.ViewHolder>() {

    lateinit var mBinding: AddSuggestionsPopupBinding


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var suggTitle: TextView = view.findViewById(R.id.sugg_item_title)
        /*var sug_popup_binding = view.findViewById<View>(R.id.add_sug_popup)
        var sug_popup_binding_editText : EditText = view.findViewById(R.id.suggestion_edit_text)*/
        var suggId: TextView = view.findViewById(R.id.sugg_item_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_spam_list, parent, false)
        mBinding = AddSuggestionsPopupBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return suglist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsSug = suglist[position]
        holder.suggTitle.text = itemsSug.sugg
        holder.itemView.setOnClickListener {
            val sqhm =
                SQLiteHelperMethods(context, "suggestion.db", null, 1, null)

            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.new_suggestion)
            builder.setView(mBinding.root)
            mBinding.suggestionEditText.setText(itemsSug.sugg)
            builder.setNegativeButton("Cancel", null)
            builder.setPositiveButton("Done",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    sqhm.updateSuggestion(
                        holder.suggId.text.toString(),
                        "'" + mBinding.suggestionEditText.text.toString()+"' "
                    )
                })
            builder.show()
        }
//        holder.suggId.text = itemsSug.id.toString()
    }
}

