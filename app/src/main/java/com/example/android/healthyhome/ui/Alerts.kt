package com.example.android.healthyhome.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class Alerts {
    companion object{
        fun basicAlert(context: Context, title: String, message: String, cancelable: Boolean, onClickFunction: (DialogInterface, Int) -> Unit): AlertDialog.Builder{

            val builder = AlertDialog.Builder(context)

            with(builder){
                setTitle(title)
                setMessage(message)
                setPositiveButton("OK", DialogInterface.OnClickListener(function = onClickFunction))
                setCancelable(cancelable)
            }

            return builder
        }

        fun cancelableAlert(context: Context, title: String, message: String, onClickFunction: (DialogInterface, Int) -> Unit): AlertDialog.Builder{
            return basicAlert(context, title, message, true, onClickFunction).setNegativeButton("Cancel", null)
        }
    }
}