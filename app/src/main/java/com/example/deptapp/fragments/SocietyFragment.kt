package com.example.deptapp.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.deptapp.R
import com.example.deptapp.adapters.EventListAdapter
import com.example.deptapp.data.EventData
import com.example.deptapp.data.MySingleton
import com.example.deptapp.databinding.FragmentSocietyBinding
import com.google.android.material.navigation.NavigationView
import kotlin.math.min


class SocietyFragment : Fragment() {

    private lateinit var binding: FragmentSocietyBinding
    private lateinit var imageList: ArrayList<SlideModel>
    private val mEventArray = ArrayList<EventData>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSocietyBinding.inflate(layoutInflater, container, false)
        imageList = ArrayList()
        fetchEventData()

        imageList.add(
            SlideModel(
                "https://pict.acm.org/pulzion19/About-us/data1/ACMlogos.png",
                "Welcome To ACM Chapter", ScaleTypes.CENTER_INSIDE

            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72",
                "ACM Hack Track Workshop", ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72",
                "ACM Members", ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Fone-min.JPG?alt=media&token=c5e88727-c810-42d9-886f-9953073ec654",
                "Prize Distribution", ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ftwo.JPG?alt=media&token=059ccf95-ede1-4787-a833-caaec8e56a91",
                "Workshop", ScaleTypes.CENTER_CROP
            )
        )
        binding.imageSliderSocietyFrag.setImageList(imageList)

//        Glide.with(binding.root.context)
//            .load("https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72")
//            .into(binding.imgSocEvent1)
//        Glide.with(binding.root.context)
//            .load("https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72")
//            .into(binding.imgSocEvent2)
        binding.tvBtnSocEventViewMore.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", "EVENTS")
            val fragment = EventFragment()
            fragment.arguments = bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame, fragment)
            transaction.commit()
            requireActivity().findViewById<NavigationView>(R.id.navigationView).checkedItem?.isChecked =
                false
        }

        return binding.root
    }

    private fun fetchEventData(){
        val url = "https://ill-moth-stole.cyclic.app/api/event/fetch"
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            {
                val eventJsonArray = it.getJSONArray("response")
//                val mEventArray = ArrayList<EventData>()
                for(i in 0 until min(3, eventJsonArray.length())){
                    val eventsJsonObject = eventJsonArray.getJSONObject(i)
                    val events = EventData(
                        eventsJsonObject.getString("_id"),
                        eventsJsonObject.getString("title"),
                        eventsJsonObject.getJSONArray("image"),
                        eventsJsonObject.getString("title"),
                        eventsJsonObject.getString("desc")
                    )
                    mEventArray.add(events)
                }
                setupEventSociety()
            },
            {
                Toast.makeText(context,"Error", Toast.LENGTH_LONG).show()
            }
        ){
        }
        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }

    private fun setupEventSociety() {

        val noticeCount = mEventArray.size

        if (noticeCount >= 1) {
            binding.llEvent1.visibility = View.VISIBLE
            val eventTitle = mEventArray[0].eventTitle
            val eventTime = mEventArray[0].eventTime

            Glide.with(binding.root.context)
                .load(mEventArray[0].eventImageUrl.getJSONObject(0)["imageurl"])
                .into(binding.imgSocEvent1)
            binding.tvSocEventTitle1.text = eventTitle
            binding.tvSocEventTime1.text = eventTime
            binding.llEvent1.setOnClickListener {
                val fragment = EventDetailsFragment()
                val bundle = Bundle()
                bundle.putString("title", eventTitle)
                bundle.putString(
                    "img1",
                    mEventArray[0].eventImageUrl.getJSONObject(0)["imageurl"].toString()
                )
                bundle.putString(
                    "img2",
                    mEventArray[0].eventImageUrl.getJSONObject(1)["imageurl"].toString()
                )
                bundle.putString(
                    "img3",
                    mEventArray[0].eventImageUrl.getJSONObject(2)["imageurl"].toString()
                )
                bundle.putString("desc", mEventArray[0].eventDesc)
                fragment.arguments = bundle
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frame, fragment)
                transaction.commit()
            }
        }
        if (noticeCount >= 2) {
            binding.llEvent2.visibility = View.VISIBLE
            val eventTitle = mEventArray[1].eventTitle
            val eventTime = mEventArray[1].eventTime

            Glide.with(binding.root.context)
                .load(mEventArray[1].eventImageUrl.getJSONObject(0)["imageurl"])
                .into(binding.imgSocEvent2)
            binding.tvSocEventTitle2.text = eventTitle
            binding.tvSocEventTime2.text = eventTime
            binding.llEvent2.setOnClickListener {
                val fragment = EventDetailsFragment()
                val bundle = Bundle()
                bundle.putString("title", eventTitle)
                bundle.putString(
                    "img1",
                    mEventArray[1].eventImageUrl.getJSONObject(0)["imageurl"].toString()
                )
                bundle.putString(
                    "img2",
                    mEventArray[1].eventImageUrl.getJSONObject(1)["imageurl"].toString()
                )
                bundle.putString(
                    "img3",
                    mEventArray[1].eventImageUrl.getJSONObject(2)["imageurl"].toString()
                )
                bundle.putString("desc", mEventArray[1].eventDesc)
                fragment.arguments = bundle
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frame, fragment)
                transaction.commit()
            }

        }
        if(noticeCount >= 3) {
            binding.llEvent3.visibility = View.VISIBLE
            val eventTitle = mEventArray[2].eventTitle
            val eventTime = mEventArray[2].eventTime

            Glide.with(binding.root.context)
                .load(mEventArray[2].eventImageUrl.getJSONObject(0)["imageurl"])
                .into(binding.imgSocEvent3)
            binding.tvSocEventTitle3.text = eventTitle
            binding.tvSocEventTime3.text = eventTime
            binding.llEvent3.setOnClickListener {
                val fragment = EventDetailsFragment()
                val bundle = Bundle()
                bundle.putString("title", eventTitle)
                bundle.putString(
                    "img1",
                    mEventArray[2].eventImageUrl.getJSONObject(0)["imageurl"].toString()
                )
                bundle.putString(
                    "img2",
                    mEventArray[2].eventImageUrl.getJSONObject(1)["imageurl"].toString()
                )
                bundle.putString(
                    "img3",
                    mEventArray[2].eventImageUrl.getJSONObject(2)["imageurl"].toString()
                )
                bundle.putString("desc", mEventArray[2].eventDesc)
                fragment.arguments = bundle
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frame, fragment)
                transaction.commit()
            }
        }
    }

}