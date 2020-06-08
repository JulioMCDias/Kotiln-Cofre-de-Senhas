package com.jlmcdeveloper.cofresenha.ui.listpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.data.model.Book
import com.jlmcdeveloper.cofresenha.ui.addpassword.AddPasswordActivity
import kotlinx.android.synthetic.main.activity_list_book.*
import kotlinx.android.synthetic.main.activity_list_password.*
import org.koin.android.ext.android.inject

class ListPasswordActivity : AppCompatActivity() {
    private val viewModel: ListPasswordViewModel by inject()
    private lateinit var bookName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_password)

        bookName = intent.getStringExtra(Book::class.java.name)!!

        setSupportActionBar(toolbar_list_book)
        val adapter =  PasswordAdapter()
        recyclerView_list_password.adapter = adapter

        // ------ update da lista -------
        viewModel.passwords.observe(this, Observer {
            adapter.updateItems(it!!.toMutableList())
        })


        btn_list_password.setOnClickListener {
            startActivity(Intent(this, AddPasswordActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.load(bookName)
    }
}