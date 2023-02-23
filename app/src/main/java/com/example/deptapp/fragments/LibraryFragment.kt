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
        binding.bookLoader.visibility=View.VISIBLE
        val url =
            "https://script.googleusercontent.com/macros/echo?user_content_key=uQVxQaStsxqFIIf-YaJcKuBTZlExM2SPRLxa_bWZHvaLKOHR33z_wKCpZxSexd5tGEZ7Rl3DAKOEGcc5XfCtP-JL4_mghDILm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnEytzYiCyJ3XJVI0jo9-Wkc30EP0fgIXyRDJRLPG-c5ZIhJLqi5hfvmTVuwYvc0RIC_Wz0-E8aGheONHA6hi9ni5BPxo4khIoA&lib=MpTEs8X7Wb53igLDKCnNo2btJ36oU3psL"
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                binding.bookLoader.visibility=View.INVISIBLE

                val newsJsonArray = it.getJSONArray("data")
                val newsArray = ArrayList<BookData>()
                for (i in 1 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = BookData(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("subject"),
                    )
                    newsArray.add(news)
                }
                mBooksListAdapter.differ.submitList(newsArray)
            },
            Response.ErrorListener {
            }
        ) {
        }

        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }
}