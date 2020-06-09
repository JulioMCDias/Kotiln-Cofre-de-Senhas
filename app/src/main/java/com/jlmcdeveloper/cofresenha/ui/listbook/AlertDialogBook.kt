package com.jlmcdeveloper.cofresenha.ui.listbook

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.jlmcdeveloper.cofresenha.R


class AlertDialogBook {
    lateinit var onClickListener: (String) -> Unit
    private lateinit var alertDialog: AlertDialog


    fun addNewBook(layoutInflater: LayoutInflater, contexts: Context){
        val view: View = layoutInflater.inflate(R.layout.alert_dialogo_book, null)

        val editText= view.findViewById<EditText>(R.id.textEdit_new_book)

        view.findViewById<Button>(R.id.btn_save_book).setOnClickListener {
            if(this::onClickListener.isInitialized and editText.text.isBlank())
                onClickListener(editText.text.toString())
            alertDialog.cancel()
        }

        view.findViewById<Button>(R.id.btn_cancel_book).setOnClickListener{
            alertDialog.cancel()
        }

        val builder: AlertDialog.Builder = AlertDialog.Builder(contexts)
        builder.setTitle(R.string.info_book)
        builder.setView(view)
        alertDialog = builder.create()
        alertDialog.show()
    }




}