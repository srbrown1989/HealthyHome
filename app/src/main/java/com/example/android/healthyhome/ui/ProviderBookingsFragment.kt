package com.example.android.healthyhome.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.healthyhome.R

class ProviderBookingsFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_bookings_fragment)
    }
}

//fun providerTable(): String {
   // return "CREATE TABLE" +
     //       Db.tableclass.PROVIDER_BOOKINGS + "(" +
     //       Db.tableclass.COLUMN_ID + " INTEGER PRIMARY KEY, " +
     //       Db.tableclass.COLUMN_NAME + " TEXT NOT NULL, " +
     //       Db.tableclass.COLUMN_CLIENT + " TEXT NOT NULL, " +
     //       Db.tableclass.COLUMN_DAY + " TEXT NOT NULL, " +
     //       Db.tableclass.COLUMN_START_TIME + " TEXT NOT NULL, " +
     //       Db.tableclass.COLUMN_END_TIME + " TEXT NOT NULL, " +

          //  "UNIQUE (" +
      //      Db.tableclass.COLUMN_ID +
         //   ") ON CONFLICT REPLACE);"
//}
