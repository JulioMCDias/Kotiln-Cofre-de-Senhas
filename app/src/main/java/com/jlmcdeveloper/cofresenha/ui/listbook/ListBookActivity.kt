package com.jlmcdeveloper.cofresenha.ui.listbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jlmcdeveloper.cofresenha.R
import kotlinx.android.synthetic.main.activity_list_book.*
import org.koin.android.ext.android.inject

class ListBookActivity : AppCompatActivity() {
    private val viewModel: ListBookViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_book)

        setSupportActionBar(toolbar_list_book)
        val adapter =  BookAdapter()
        recyclerView_list_book.adapter = adapter

        // ------ update da lista -------
        viewModel.books.observe(this, Observer {
            adapter.updateItems(it!!.toMutableList())
        })



        btn_list_book.setOnClickListener {
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.load()
    }
}