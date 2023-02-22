package com.example.deptapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.deptapp.adapters.BooksListAdapter
import com.example.deptapp.adapters.TeachersListAdapter
import com.example.deptapp.data.BookData
import com.example.deptapp.data.MySingleton
import com.example.deptapp.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {

    private lateinit var binding: FragmentLibraryBinding
    private lateinit var mBooksListAdapter: BooksListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLibraryBinding.inflate(layoutInflater, container, false)
        fetchData()
        setupBookList()
        return binding.root
    }
    private fun setupBookList() {
        mBooksListAdapter = BooksListAdapter()
        binding.rvBooks.adapter = mBooksListAdapter
        binding.rvBooks.layoutManager = LinearLayoutManager(binding.root.context)
    }

    private fun fetchData() {
        val url =
            "https://script.google.com/macros/s/AKfycbzjpnA_Ufjcc6PavY-8WimLbS95D7CwCo_owwEwzMCJVyYG9u3GEShND-hR9wKyiw-T/exec"

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {

                val newsJsonArray = it.getJSONArray("data")
                val newsArray = ArrayList<BookData>()
                for (i in 1 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = BookData(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("category"),
                    )
                    newsArray.add(news)
                }
                mBooksListAdapter.updateNews(newsArray)
            },
            Response.ErrorListener {
            }
        ) {
        }

        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }
}