package com.example.deptapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.deptapp.adapters.BooksListAdapter
import com.example.deptapp.data.BookData
import com.example.deptapp.data.MySingleton
import com.example.deptapp.databinding.FragmentLibraryBinding
import com.example.deptapp.util.ConnectionManager

class LibraryFragment : Fragment() {

    private lateinit var binding: FragmentLibraryBinding
    private lateinit var mBooksListAdapter: BooksListAdapter
    private var booksList = ArrayList<BookData>()
    private var filterBooks = ArrayList<BookData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLibraryBinding.inflate(layoutInflater, container, false)
        fetchData()
        setupBookList()

        binding.searchBook.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(msg: String): Boolean {
                binding.tv2.visibility=View.INVISIBLE
                filterBooks.clear()
                filter(msg)
                return false
            }
        })
        return binding.root
    }

    private fun filter(text: String) {
        if (text.isEmpty()) {
            binding.tv2.visibility=View.VISIBLE
            mBooksListAdapter.differ.submitList(booksList)
            binding.rvBooks.adapter = mBooksListAdapter
            mBooksListAdapter.notifyDataSetChanged()
        } else {
            for (book in booksList) {
                if (book.bookTitle.lowercase().contains(text.lowercase())) {
                    filterBooks.add(book)
                }
            }
            mBooksListAdapter.differ.submitList(filterBooks)
            binding.rvBooks.adapter = mBooksListAdapter
            mBooksListAdapter.notifyDataSetChanged()
        }
    }

    private fun setupBookList() {
        mBooksListAdapter = BooksListAdapter()
        binding.rvBooks.adapter = mBooksListAdapter
        binding.rvBooks.layoutManager = LinearLayoutManager(binding.root.context)
    }

    private fun fetchData() {
        binding.bookLoader.visibility = View.VISIBLE
        val url =
            "https://script.google.com/macros/s/AKfycbzjpnA_Ufjcc6PavY-8WimLbS95D7CwCo_owwEwzMCJVyYG9u3GEShND-hR9wKyiw-T/exec"

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                binding.bookLoader.visibility = View.INVISIBLE

                val bookJsonArray = it.getJSONArray("data")
                val booksArray = ArrayList<BookData>()
                for (i in 1 until bookJsonArray.length()) {
                    val bookJsonObject = bookJsonArray.getJSONObject(i)
                    val news = BookData(
                        bookJsonObject.getString("title"),
                        bookJsonObject.getString("author"),
                        bookJsonObject.getString("category"),
                    )
                    booksArray.add(news)
                }
                booksList = booksArray
                mBooksListAdapter.differ.submitList(booksArray)
            },
            Response.ErrorListener {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }
        ) {
        }

        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }
}