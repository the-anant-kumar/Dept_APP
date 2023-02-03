package com.example.deptapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.deptapp.Fragments.*
import com.example.deptapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        openDashboard()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            binding.drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.admin -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AdminFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.faculty -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, FacultyFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.students -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, StudentsFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.academics -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AcademicsFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.library -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, LibraryFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.alumni -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AlumniFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.society -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, SocietyFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame)
        when (frag) {
            !is HomeFragment -> {
                openDashboard()
                binding.navigationView.checkedItem?.isChecked = false
            }
            else -> super.onBackPressed()
        }
    }

    private fun openDashboard()
    {
        val fragment= HomeFragment()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment)
        transaction.commit()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == android.R.id.home) {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}