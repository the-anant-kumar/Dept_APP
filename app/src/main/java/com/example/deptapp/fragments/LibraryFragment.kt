package com.example.deptapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deptapp.adapters.BooksListAdapter
import com.example.deptapp.adapters.TeachersListAdapter
import com.example.deptapp.databinding.FragmentLibraryBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LibraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibraryFragment : Fragment() {

    private lateinit var binding: FragmentLibraryBinding
    private lateinit var booksListAdapter: BooksListAdapter

    var itemLists = arrayListOf(
        Triple("Soumen Paul", "Head of Department", "spaul234@gmail.com"),
        Triple("Manasija Bhattacharya", "Asst. Professor", "mb332@gmail.com"),
        Triple("Tamosa Chakraborty", "Asst. Professor", "tamosa32it@gmail.com"),
        Triple("Banani Ghosh", "Asst. Professor", "banani23@gmail.com"),
        Triple("Ramkrishna Ghosh","Assoc. Professor", "ramkrishna@gmail.com"),
        Triple("Pijush Bair","Asst. Professor", "pbairi@gmail.com"),
        Triple("Debolina Ghosh","Asst. Professor", "dghosh@gmail.com"),
        Triple("Arundhati Bhowal", "Asst. Professor","ab23@gmail.com"),
        Triple("Bidyut Das ", "Assoc. Professor", "bidyut23@gmail.com"),
        Triple("Moumita Ghosh", "Asst. Professor", "mgh@gmail.com")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLibraryBinding.inflate(layoutInflater, container, false)
        setupBookList()
        return binding.root
    }
    private fun setupBookList() {
        booksListAdapter = BooksListAdapter(itemLists)
        binding.rvBooks.adapter = booksListAdapter
        binding.rvBooks.layoutManager = LinearLayoutManager(binding.root.context)
    }
}