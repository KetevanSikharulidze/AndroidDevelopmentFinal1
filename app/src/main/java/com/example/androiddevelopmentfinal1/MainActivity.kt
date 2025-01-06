package com.example.androiddevelopmentfinal1

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androiddevelopmentfinal1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var receiver: AirplaneModeChangeReceiver

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Broadcast Receiver Registration
        receiver = AirplaneModeChangeReceiver()

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }

        //SharedPreferences

        val sharedPreferences = getSharedPreferences("MyApp",Context.MODE_PRIVATE)

        binding.apply {

            textView.text = sharedPreferences.getString("NOTE","")

            button.setOnClickListener {
                val note = editText.text.toString()

                if (!TextUtils.isEmpty(note)) {
                    val text = textView.text.toString()
                    val resultText = note + "\n" + text
                    textView.text = resultText
                    editText.setText("")

                    sharedPreferences.edit().putString("NOTE",resultText).apply()

                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}