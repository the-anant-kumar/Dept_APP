package com.example.deptapp.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.deptapp.R
import com.example.deptapp.adapters.EventList2Adapter
import com.example.deptapp.adapters.EventListAdapter
import com.example.deptapp.adapters.NoticeListAdapter
import com.example.deptapp.databinding.FragmentHomeBinding
import com.google.android.material.navigation.NavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var imageList: ArrayList<SlideModel>
    lateinit var eventListAdapter: EventList2Adapter
    var itemLists = arrayListOf(
        "Minutes of the 140th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setUpEvent()

        binding.btnWebsite.setOnClickListener {
            // custom browse
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse("https://hithaldia.ac.in/"))
        }

        binding.btnFaculity.setOnClickListener {
            val fragment= FacultyFragment()
            val transaction=requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame,fragment)
            transaction.commit()
            activity?.findViewById<NavigationView>(R.id.navigationView)?.setCheckedItem(R.id.faculty)
        }

        binding.btnAcademics.setOnClickListener {
            val fragment= AcademicsFragment()
            val transaction=requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame,fragment)
            transaction.commit()
            requireActivity().findViewById<NavigationView>(R.id.navigationView)?.setCheckedItem(R.id.academics)
        }

        binding.tvBtnEventViewMore.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("name","EVENTS")
            val fragment = EventFragment()
            fragment.arguments=bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame, fragment)
            transaction.commit()
            requireActivity().findViewById<NavigationView>(R.id.navigationView).checkedItem?.isChecked = false
        }
        binding.tvBtnNoticeViewMore.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("name","NOTICE")
            val fragment = EventFragment()
            fragment.arguments=bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame, fragment)
            transaction.commit()
            requireActivity().findViewById<NavigationView>(R.id.navigationView).checkedItem?.isChecked = false
        }
        binding.marqueeText.isSelected = true
        imageList = ArrayList()

        imageList.add(
            SlideModel(
                "https://www.admissionfever.com/Media/clgimg/gallery/2092_img7281787176718769.png",
                "Haldia Institute Of Technology"
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72",
                "ACM Hack Track Workshop"
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72",
                "ACM Members"
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Fone-min.JPG?alt=media&token=c5e88727-c810-42d9-886f-9953073ec654",
                "Prize Distribution"
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ftwo.JPG?alt=media&token=059ccf95-ede1-4787-a833-caaec8e56a91",
                "Workshop"
            )
        )
        binding.imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
        binding.imageSlider.textAlignment=

        return binding.root
    }

    private fun setUpEvent()
    {
        eventListAdapter = EventList2Adapter(itemLists)
        binding.rvEvents.adapter=eventListAdapter
        binding.rvEvents.layoutManager= LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
    }

}