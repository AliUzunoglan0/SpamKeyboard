package com.example.spamkeyboard.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spamkeyboard.R
import com.example.spamkeyboard.adapters.SuggestionAdapter
import com.example.spamkeyboard.databinding.AddSuggestionsPopupBinding
import com.example.spamkeyboard.databinding.FragmentSuggestionBinding
import com.example.spamkeyboard.methods.SQLiteHelperMethods
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SuggestionFragment : Fragment(), View.OnClickListener {


    lateinit var sugBinding: FragmentSuggestionBinding
    lateinit var sug_popup_binding: AddSuggestionsPopupBinding
    lateinit var adapter: SuggestionAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var dividerItemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sugBinding = FragmentSuggestionBinding.inflate(layoutInflater)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        linearLayoutManager = LinearLayoutManager(context)
        dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_suggestion, container, false)
        val btn: FloatingActionButton = view.findViewById(R.id.add_fab)
        btn.setOnClickListener(this)

        return view

    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onResume() {
        super.onResume()

        just()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun just() {
        val sqhm = SQLiteHelperMethods(context, "suggestion.db", null, 1, null)
        adapter = SuggestionAdapter(context, sqhm.showSuggestions())
        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.suggestion_recycler_view)
        mRecyclerView?.adapter = adapter
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView?.layoutManager = linearLayoutManager
        mRecyclerView?.addItemDecoration(dividerItemDecoration)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.add_fab -> {

                sug_popup_binding = AddSuggestionsPopupBinding.inflate(layoutInflater)
                val sqhm: SQLiteHelperMethods =
                    SQLiteHelperMethods(context, "suggestion.db", null, 1, null)

                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder.setTitle(R.string.new_suggestion)
                builder.setView(sug_popup_binding.root)
                builder.setNegativeButton("Cancel", null)
                builder.setPositiveButton("Done",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        sqhm.addSuggestion(sug_popup_binding.suggestionEditText.text.toString())
                        onResume()
                    })
                builder.show()

            }
        }
    }

}


