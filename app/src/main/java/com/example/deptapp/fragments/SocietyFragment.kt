package com.example.deptapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.deptapp.R
import com.example.deptapp.data.EventData
import com.example.deptapp.data.MySingleton
import com.example.deptapp.databinding.FragmentSocietyBinding
import com.google.android.material.navigation.NavigationView
import kotlin.math.min


class SocietyFragment : Fragment() {

    private lateinit var binding: FragmentSocietyBinding
    private lateinit var imageList: ArrayList<SlideModel>
    private val mEventArray = ArrayList<EventData>()
    val TAG = "SOCIETY FRAGMENT"


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
                        eventsJsonObject.getString("date"),
                        eventsJsonObject.getString("desc")
                    )
                    mEventArray.add(events)
                }
                setupEventSociety()
            },
            {
//                Toast.makeText(context,"Error", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Error")
            }
        ){
        }
        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }

    private fun setupEventSociety() {

        val eventCount = mEventArray.size

        if (eventCount >= 1) {
            binding.llEvent1.visibility = View.VISIBLE
            val eventTitle = mEventArray[0].eventTitle
            val eventTime = mEventArray[0].eventTime

            Glide.with(binding.root.context)
                .load(mEventArray[0].eventImageUrl.getJSONObject(0)["imageurl"])
                .into(binding.imgSocEvent1)
            binding.tvSocEventTitle1.text = eventTitle
            binding.tvSocEventTime1.text = eventTime.subSequence(0,10)
            binding.llEvent1.setOnClickListener {
                val fragment = EventDetailsFragment()
                val bundle = Bundle()
                bundle.putString("title", eventTitle)
                bundle.putString(
                    "img1",
                    mEventArray[0].eventImageUrl.getJSONObject(0)["imageurl"].toString()
                )
                if(mEventArray[0].eventImageUrl.length() >= 2) {
                    bundle.putString(
                        "img2",
                        mEventArray[0].eventImageUrl.getJSONObject(1)["imageurl"].toString()
                    )
                }
                if(mEventArray[0].eventImageUrl.length() >= 3) {
                    bundle.putString(
                        "img3",
                        mEventArray[0].eventImageUrl.getJSONObject(2)["imageurl"].toString()
                    )
                }
                bundle.putString("desc", mEventArray[0].eventDesc)
                bundle.putString("onBackPressedToken", "SOCIETY")
                fragment.arguments = bundle
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frame, fragment)
                transaction.commit()
            }
        }
        if (eventCount >= 2) {
            binding.llEvent2.visibility = View.VISIBLE
            val eventTitle = mEventArray[1].eventTitle
            val eventTime = mEventArray[1].eventTime.subSequence(0,10)

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
                if(mEventArray[1].eventImageUrl.length() >= 2) {
                    bundle.putString(
                        "img2",
                        mEventArray[1].eventImageUrl.getJSONObject(1)["imageurl"].toString()
                    )
                }
                if(mEventArray[1].eventImageUrl.length() >= 3) {
                    bundle.putString(
                        "img3",
                        mEventArray[1].eventImageUrl.getJSONObject(2)["imageurl"].toString()
                    )
                }
                bundle.putString("desc", mEventArray[1].eventDesc)
                bundle.putString("onBackPressedToken", "SOCIETY")
                fragment.arguments = bundle
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frame, fragment)
                transaction.commit()
            }

        }
        if(eventCount >= 3) {
            binding.llEvent3.visibility = View.VISIBLE
            val eventTitle = mEventArray[2].eventTitle
            val eventTime = mEventArray[2].eventTime.subSequence(0,10)

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
                if(mEventArray[2].eventImageUrl.length() >= 2) {
                    bundle.putString(
                        "img2",
                        mEventArray[2].eventImageUrl.getJSONObject(1)["imageurl"].toString()
                    )
                }
                if(mEventArray[2].eventImageUrl.length() >= 3) {
                    bundle.putString(
                        "img3",
                        mEventArray[2].eventImageUrl.getJSONObject(2)["imageurl"].toString()
                    )
                }
                bundle.putString("desc", mEventArray[2].eventDesc)
                bundle.putString("onBackPressedToken", "SOCIETY")
                fragment.arguments = bundle
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frame, fragment)
                transaction.commit()
            }
        }
    }
}