package com.jlmcdeveloper.cofresenha.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object Utils {
    fun closeKeyboard(context: Context, vararg editTexts: EditText){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        for (eT in editTexts)
            imm.hideSoftInputFromWindow(eT.windowToken, 0)
    }

}