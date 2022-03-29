package com.example.android.healthyhome.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View

class Alerts {
    companion object{
        fun basicAlert(context: Context, title: String, message: String, cancelable: Boolean, onClickFunction: (DialogInterface, Int) -> Unit){

            val builder = AlertDialog.Builder(context)

            with(builder){
                setTitle(title)
                setMessage(message)
                setPositiveButton("OK", DialogInterface.OnClickListener(function = onClickFunction))
                setCancelable(cancelable)
                show()
            }

        }
    }
}