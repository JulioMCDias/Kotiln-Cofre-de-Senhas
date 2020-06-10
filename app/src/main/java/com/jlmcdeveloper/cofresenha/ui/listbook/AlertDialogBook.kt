package com.jlmcdeveloper.cofresenha.ui.listbook

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.jlmcdeveloper.cofresenha.R


class AlertDialogBook {
    lateinit var onClickListener: (String, Int) -> Unit
    private lateinit var alertDialog: AlertDialog


    fun addNewBook(layoutInflater: LayoutInflater, context: Context){
        editableBook(layoutInflater, context, "", -1)
    }


    fun editableBook(layoutInflater: LayoutInflater, context: Context, text: String, position: Int){
        val view: View = layoutInflater.inflate(R.layout.alert_dialogo_book, null)

        val editText= view.findViewById<EditText>(R.id.textEdit_new_book)
        editText.setText(text)

        view.findViewById<Button>(R.id.btn_save_book).setOnClickListener {
            if(this::onClickListener.isInitialized and editText.text.isNotBlank())
                onClickListener(editText.text.toString(), position)
            alertDialog.cancel()
        }

        view.findViewById<Button>(R.id.btn_cancel_book).setOnClickListener{
            alertDialog.cancel()
        }

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.info_book)
        builder.setView(view)
        alertDialog = builder.create()
        alertDialog.show()
    }

}