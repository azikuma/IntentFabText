package com.example.intentfabtext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.intentfabtext.databinding.ActivityMainBinding
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        realm = Realm.getDefaultInstance()

        setText()

//        val message = intent.getStringExtra("MESSAGE")

        binding.addButton.setOnClickListener {
            onAddButtonTapped(it)
        }

//        if (message == null) {
//            binding.textView.text = ""
//        } else {
//            var m = message.toString()
//            binding.textView.text = m + "\n" + m.take(5)
//        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun onAddButtonTapped(view: View?) {
        val intent = Intent(this, TextFieldActivity::class.java)
        startActivity(intent)
    }

    fun setText() {
        var textList = realm.where(Memo::class.java).findAll()
//        val stexts = textList.map { it.text }
        if (textList.isEmpty()) {
            binding.textView.text = ""
        }else {
            binding.textView.text = textList[0]?.text

        }
    }
}