package com.jlmcdeveloper.cofresenha.ui.listpassword

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.data.model.Book
import com.jlmcdeveloper.cofresenha.ui.addpassword.AddPasswordActivity
import com.jlmcdeveloper.cofresenha.utils.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.activity_list_book.*
import kotlinx.android.synthetic.main.activity_list_password.*
import org.koin.android.ext.android.inject

class ListPasswordActivity : AppCompatActivity() {
    private val viewModel: ListPasswordViewModel by inject()
    private lateinit var bookName: String
    private lateinit var adapter: PasswordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_password)

        bookName = intent.getStringExtra(Book::class.java.name)!!

        setSupportActionBar(toolbar_list_password)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = bookName

        adapter =  PasswordAdapter()
        recyclerView_list_password.adapter = adapter

        // ------ update da lista -------
        viewModel.passwords.observe(this, Observer {
            adapter.updateItems(it!!.toMutableList())
        })

        //----------- visibilidade ----------------
        viewModel.loadingVisibility.observe(this, Observer {
            layout_loading_password.visibility = if(it) View.VISIBLE else View.GONE
        })


        btn_list_password.setOnClickListener {
            startActivity(Intent(this, AddPasswordActivity::class.java))
        }

        enableSwipeToDeleteAndUndo()
    }

    override fun onStart() {
        super.onStart()
        viewModel.load(bookName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }


    private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {

                val position: Int = viewHolder.adapterPosition
                val item = viewModel.passwords.value!![position]
                adapter.removeItem(position)
                viewModel.removeItem(item)
                val snackbar: Snackbar = Snackbar
                    .make(
                        coordinatorLayout_list_password,
                        "O item foi removido da lista.",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.setAction("Desfazer") {
                    adapter.restoreItem(item, position)
                    viewModel.restoreItem(item, position)
                    recyclerView_list_password.scrollToPosition(position)
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(recyclerView_list_password)
    }
}