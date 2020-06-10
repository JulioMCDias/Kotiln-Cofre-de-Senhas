package com.jlmcdeveloper.cofresenha.ui.listbook

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jlmcdeveloper.cofresenha.data.model.Book
import com.jlmcdeveloper.cofresenha.databinding.ItemCardBookBinding
import com.jlmcdeveloper.cofresenha.ui.base.BaseViewHolder
import com.jlmcdeveloper.cofresenha.ui.listpassword.ListPasswordActivity

class BookAdapter(private val books: MutableList<Book> = mutableListOf()) :
    RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var editableBook: (Book) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        return BookHolder(context, ItemCardBookBinding.inflate(inflater, parent, false),
        editableBook)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(books[position])
    }


    fun removeItem(position: Int) {
        books.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: Book, position: Int) {
        books.add(position, item)
        notifyItemInserted(position)
    }

    //-------------- atualização da lista ---------------
    fun updateItems(listNote: MutableList<Book>) {
        books.clear()
        books.addAll(listNote)
        notifyDataSetChanged()
    }


    //====================== ViewHolder =============================
    class BookHolder(
        private val context: Context,
        private val binding: ItemCardBookBinding, val editableBook : (Book) -> Unit) : BaseViewHolder(binding.root) {

        override fun bind(item: Any?) {
            binding.book = item as Book?
            binding.executePendingBindings()

            // ----- abrir na caderno -----
            binding.cardBook.setOnClickListener {
                context.startActivity(Intent(context, ListPasswordActivity::class.java)
                    .putExtra(Book::class.java.name, (item as Book).name))
            }

            binding.cardBook.setOnLongClickListener {
                editableBook(item as Book)
                return@setOnLongClickListener true
            }
        }
    }

}
