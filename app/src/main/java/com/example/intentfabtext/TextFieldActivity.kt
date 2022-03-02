package com.example.intentfabtext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.intentfabtext.databinding.ActivityTextFieldBinding
import io.realm.Realm
import org.w3c.dom.Text

class TextFieldActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextFieldBinding
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextFieldBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        realm = Realm.getDefaultInstance()

        binding.completeButton.setOnClickListener {
            if (binding.editField.text.isEmpty()) {
                return@setOnClickListener
            }
            onCompleteButtonTapped(it)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun onCompleteButtonTapped(view: View?) {
        val message = binding.editField.text.toString()
        realm.executeTransactionAsync {
//            val memo = it.createObject(Memo::class.java)
            val memo = it.createObject(Memo::class.java)
            memo.text = message
            it.copyFromRealm(memo)
        }

        binding.editField.text.clear()

        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra("MESSAGE", message)
        startActivity(intent)
    }
}