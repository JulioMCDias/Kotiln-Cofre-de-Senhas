package com.jlmcdeveloper.cofresenha.ui.listpassword

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jlmcdeveloper.cofresenha.data.model.Password
import com.jlmcdeveloper.cofresenha.databinding.ItemCardPasswordBinding
import com.jlmcdeveloper.cofresenha.ui.addpassword.AddPasswordActivity
import com.jlmcdeveloper.cofresenha.ui.base.BaseViewHolder

class PasswordAdapter(private val passwords: MutableList<Password> = mutableListOf()) :
    RecyclerView.Adapter<BaseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        return PasswordHolder(context, ItemCardPasswordBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return passwords.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(passwords[position])
    }

    //-------------- atualização da lista ---------------
    fun updateItems(listNote: MutableList<Password>) {
        passwords.clear()
        passwords.addAll(listNote)
        notifyDataSetChanged()
    }


    //====================== ViewHolder =============================
    class PasswordHolder(
        private val context: Context,
        private val binding: ItemCardPasswordBinding) : BaseViewHolder(binding.root) {

        override fun bind(item: Any?) {
            binding.password = item as Password?
            binding.executePendingBindings()

            // ----- abrir na caderno -----
            binding.cardPassword.setOnClickListener {
                context.startActivity(
                    Intent(context, AddPasswordActivity::class.java))
            }
        }
    }

}