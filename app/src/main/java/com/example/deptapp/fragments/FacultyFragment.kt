package com.example.deptapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.example.deptapp.R
import com.example.deptapp.adapters.TeacherItemClicked
import com.example.deptapp.adapters.TeachersListAdapter
import com.example.deptapp.data.MySingleton
import com.example.deptapp.data.TeacherData
import com.example.deptapp.databinding.FragmentFacultyBinding
import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.ViewHolder

class FacultyFragment : Fragment(), TeacherItemClicked {

    private lateinit var binding: FragmentFacultyBinding
    private lateinit var mTeachersListAdapter: TeachersListAdapter
    lateinit var dialogPlus: DialogPlus

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFacultyBinding.inflate(layoutInflater, container, false)

        fetchData()
        setupTeachersList()

        return binding.root
    }

    private fun setupTeachersList() {
        mTeachersListAdapter = TeachersListAdapter(this)
        binding.rvTeacher.adapter = mTeachersListAdapter
        binding.rvTeacher.layoutManager = LinearLayoutManager(binding.root.context)
    }

    private fun fetchData(){
        binding.teacherLoader.visibility = View.VISIBLE
        val url = "https://ill-moth-stole.cyclic.app/api/teacher/fetch"
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                binding.teacherLoader.visibility = View.INVISIBLE
                val teachersJsonArray = it.getJSONArray("tres")
                val mTeachersArray = ArrayList<TeacherData>()
                for(i in 0 until teachersJsonArray.length()){
                    val teachersJsonObject = teachersJsonArray.getJSONObject(i)
                    val teachers = TeacherData(
                        teachersJsonObject.getString("name"),
                        teachersJsonObject.getString("filename"),
                        teachersJsonObject.getString("designation"),
                        teachersJsonObject.getString("email"),
                        teachersJsonObject.getString("mobile"),
                        teachersJsonObject.getString("gender"),
                        teachersJsonObject.getString("education")
                    )
                    mTeachersArray.add(teachers)
                }
                mTeachersListAdapter.differ.submitList(mTeachersArray)
//                mAdapter.updateNews(mTeachersArray)
            },
            {
                Log.d("Error: ", it.toString())
            }
        ){
        }
        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }

    private fun TeacherPopup(item: TeacherData) {
        dialogPlus = DialogPlus.newDialog(binding.root.context).setContentHolder(ViewHolder(R.layout.teacher_popup))
            .setExpanded(true,1000)
            .setCancelable(true)
            .create()

        dialogPlus.show()

        val view = dialogPlus.holderView
        val image = view.findViewById<ImageView>(R.id.ivTeacherPopup)
        val name = view.findViewById<TextView>(R.id.tvNameTeacherPopup)
        val designation = view.findViewById<TextView>(R.id.tvDesignationTeacherPopup)
        val gender = view.findViewById<TextView>(R.id.tvGenderTeacherPopup)
        val specialization = view.findViewById<TextView>(R.id.tvSpecializationTeacherPopup)
        val phone = view.findViewById<TextView>(R.id.tvMobTeacherPopup)
        val email = view.findViewById<TextView>(R.id.tvEmailTeacherPopup)

        Glide.with(image)
            .load("https://thumbs.dreamstime.com/b/businessman-profile-icon-male-portrait-flat-design-vector-illustration-47075259.jpg")
            .into(image)
        name.text = item.teacherName
        designation.text = item.teacherDesignation
        gender.text = item.teacherGender
        specialization.text = item.teacherSpecialization
        phone.text = item.teacherPhoneNo
        email.text = item.teacherEmail
    }

    override fun onItemClick(item: TeacherData) {
       TeacherPopup(item)
    }
}