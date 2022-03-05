package com.example.intentfabtext

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intentfabtext.databinding.ActivityMainBinding
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var realm: Realm
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        realm = Realm.getDefaultInstance()

        binding.titleList?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = MainViewAdapter(
                generateItemList(),
                object : MainViewAdapter.ListListener {
                    override fun onClickItem(tappedView: View, itemModel: ItemModel) {
                        return@onClickItem
                    }
                }
            )
        }

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
        this.recyclerView?.adapter = null
        this.recyclerView = null
        realm.close()
    }

    private fun generateItemList(): List<ItemModel> {
        val itemList = mutableListOf<ItemModel>()
        for (i in 0..100) {
            val item: ItemModel = ItemModel().apply {
                text = "わっきゃい${i}人目"
            }
            itemList.add(item)
        }
        return itemList
    }

    private fun onClickItem(tappedView: View, itemModel: ItemModel) {

    }

    private fun onAddButtonTapped(view: View?) {
        val intent = Intent(this, TextFieldActivity::class.java)
        startActivity(intent)
    }

//    fun setText() {
//        var textList = realm.where(Memo::class.java).findAll()
//        if (textList.isEmpty()) {
//            binding.textView.text = ""
//        }else {
//            binding.textView.text = textList[0]?.text
//
//        }
//    }
}