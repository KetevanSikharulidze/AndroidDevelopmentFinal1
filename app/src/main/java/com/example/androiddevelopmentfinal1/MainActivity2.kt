package com.example.androiddevelopmentfinal1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androiddevelopmentfinal1.SQLite.DBHelper
import com.example.androiddevelopmentfinal1.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {

            add.setOnClickListener {

                val db = DBHelper(this@MainActivity2, null)

                val etName = name.text.toString()

                db.addName(etName)

                Toast.makeText(this@MainActivity2, "$etName is added", Toast.LENGTH_SHORT).show()
//                name.setText("")
                name.text.clear()
            }

            print.setOnClickListener {

                val db = DBHelper(this@MainActivity2, null)

                val cursor = db.getName()

                cursor!!.moveToFirst()
                result.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL)) + "\n")

                while (cursor.moveToNext()){
                    result.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL)) + "\n")
                }

                cursor.close()
            }

        }

    }
}