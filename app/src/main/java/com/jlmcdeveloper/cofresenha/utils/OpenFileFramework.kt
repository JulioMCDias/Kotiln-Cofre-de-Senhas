package com.jlmcdeveloper.cofresenha.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log


object OpenFileFramework {
    const val READ_REQUEST_CODE: Int = 42
    const val WRITE_REQUEST_CODE: Int = 43
    const val EDIT_REQUEST_CODE: Int = 44
    const val TAG = "TAG OPEN FILE"

    data class File(val nameFile: String, val size: String)

    //------------ler arquivo ------------
    fun performFileSearch(mimeType: String, activity: Activity) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            // Filter to show only images, using the image MIME data type.
            // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
            // To search for all documents available via installed storage providers,
            //type = "image/*"
            // it would be "*".
            type = mimeType
        }
        activity.startActivityForResult(intent,
            READ_REQUEST_CODE
        )
    }


    // ------------ criar arquivo ------
    fun createFile(mimeType: String, fileName: String, activity: Activity) {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            // Filter to only show results that can be "opened", such as
            // a file (as opposed to a list of contacts or timezones).
            addCategory(Intent.CATEGORY_OPENABLE)

            // Create a file with the requested MIME type.
            type = mimeType
            putExtra(Intent.EXTRA_TITLE, fileName)
        }
        activity.startActivityForResult(intent, WRITE_REQUEST_CODE)
    }


    // ------------ editar arquivo ----------
    fun editDocument(mimeType: String, activity: Activity) {
        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's
        // file browser.
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            // Filter to only show results that can be "opened", such as a
            // file (as opposed to a list of contacts or timezones).
            addCategory(Intent.CATEGORY_OPENABLE)

            // Filter to show only text files.
            type = mimeType
        }
        activity.startActivityForResult(intent, EDIT_REQUEST_CODE)
    }


    // ler metadados da leitura (name file, size)
    fun dumpMetaData(uri: Uri, context: Context): File{
        val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null, null)

        cursor?.use {
            if (it.moveToFirst()) {

                val displayName: String =
                    it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                //Log.i(TAG, "Display Name: $displayName")

                val sizeIndex: Int = it.getColumnIndex(OpenableColumns.SIZE)
                val size: String = if (!it.isNull(sizeIndex)) {
                    it.getString(sizeIndex)
                } else {
                    "Unknown"
                }
                //Log.i(TAG, "Size: $size")
                return File(displayName, size)
            }
        }
        return File("Unknown","Unknown")
    }
}