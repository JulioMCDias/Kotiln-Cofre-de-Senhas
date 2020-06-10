package com.jlmcdeveloper.cofresenha.ui.listbook

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.ui.main.MainActivity
import com.jlmcdeveloper.cofresenha.utils.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.activity_list_book.*
import org.koin.android.ext.android.inject

class ListBookActivity : AppCompatActivity() {
    private val viewModel: ListBookViewModel by inject()
    private val dialogBook: AlertDialogBook by inject()
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_book)

        setSupportActionBar(toolbar_list_book)

        adapter =  BookAdapter()
        recyclerView_list_book.adapter = adapter


        // ------ editar caderno -------
        adapter.editableBook ={
            dialogBook.editableBook(layoutInflater, this, it.name,
                viewModel.books.value!!.indexOf(it))
        }


        // ------ update da lista -------
        viewModel.books.observe(this, Observer {
            adapter.updateItems(it!!.toMutableList())
        })




        // ------ add novo caderno -------
        btn_list_book.setOnClickListener {
            dialogBook.addNewBook(layoutInflater, this)
        }
        // ------ salvar novo caderno  -------
        dialogBook.onClickListener = { book, pos ->
            if(pos == -1)
                viewModel.addBook(book)
            else
                viewModel.update(book, pos)
        }

        enableSwipeToDeleteAndUndo()
    }

    override fun onStart() {
        super.onStart()
        viewModel.load()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_logout -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                viewModel.logout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.list_book, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun enableSwipeToDeleteAndUndo() {
       val swipeToDeleteCallback = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {

                val position: Int = viewHolder.adapterPosition
                val item = viewModel.books.value!![position]
                adapter.removeItem(position)
                viewModel.removeItem(item)
                val snackbar: Snackbar = Snackbar
                    .make(
                        coordinatorLayout_list_book,
                        "O item foi removido da lista.",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.setAction("Desfazer") {
                    adapter.restoreItem(item, position)
                    viewModel.restoreItem(item, position)
                    recyclerView_list_book.scrollToPosition(position)
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(recyclerView_list_book)
    }
}