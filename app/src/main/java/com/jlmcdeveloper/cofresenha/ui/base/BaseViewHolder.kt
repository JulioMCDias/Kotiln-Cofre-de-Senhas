package com.jlmcdeveloper.cofresenha.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
    abstract fun bind(item: Any?)
}