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

class BookAdapter(private val books: MutableList<Book> = ArrayList()) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        return BookHolder(context, ItemCardBookBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(books[position])
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
        private val binding: ItemCardBookBinding) : BaseViewHolder(binding.root) {

        override fun bind(item: Any?) {
            binding.book = item as Book?
            binding.executePendingBindings()

            // ----- abrir na caderno -----
            binding.cardBook.setOnClickListener {
                context.startActivity(Intent(context, ListPasswordActivity::class.java))
            }
        }
    }

}
