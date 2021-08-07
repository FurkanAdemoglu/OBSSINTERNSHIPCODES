package com.example.apimvvm.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apimvvm.R
import com.example.apimvvm.ext.hideKeyboard


@SuppressLint("NotifyDataSetChanged")
class MainActivity : AppCompatActivity() {
    companion object{
        const val SEARCH_KEY="searchKey"
    }

    private val viewModel: MainViewModel by viewModels()

    private lateinit var editTextInput: EditText
    private lateinit var buttonSearch: Button
    private lateinit var recycler: RecyclerView

    private var adapter=TvShowAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        observeViewModel()
    }
    private fun initViews() {
        editTextInput = findViewById(R.id.input)
        buttonSearch = findViewById(R.id.button)
        editTextInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                buttonSearch.isEnabled = s.toString().isNotBlank()
            }
        })
        buttonSearch.setOnClickListener {
            val searchKey = editTextInput.text.toString()
            viewModel.getTvShowsBySearchKey(searchKey)
            hideKeyboard()
        }

        recycler = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.showListLiveData.observe(this) {
            adapter.apply {
                list = it
                notifyDataSetChanged()
            }
        }
        viewModel.errorStateLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

}