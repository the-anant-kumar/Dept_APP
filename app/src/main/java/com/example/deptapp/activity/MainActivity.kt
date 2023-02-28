package com.example.deptapp.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.deptapp.R
import com.example.deptapp.databinding.ActivityMainBinding
import com.example.deptapp.fragments.*

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

//        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener {
            binding.navigationView.checkedItem?.isChecked = true
            when (it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, HomeFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.admin -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, LogInFragment())
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
                R.id.placement -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, PlacementFragment())
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
                R.id.about -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AboutFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawers()
        } else {
            when (supportFragmentManager.findFragmentById(R.id.frame)) {
                !is HomeFragment -> {
                    openDashboard()
                }
                else -> super.onBackPressed()
            }
        }
    }

    private fun openDashboard() {
        val fragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
        binding.navigationView.checkedItem?.isChecked = true
        binding.navigationView.setCheckedItem(R.id.home)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        if (id == R.id.notification) {
            val bundle = Bundle()
            bundle.putString("name", "NOTICE")
            val fragment = EventFragment()
            fragment.arguments = bundle
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame, fragment)
            transaction.commit()
            binding.navigationView.checkedItem?.isChecked = false
        }
        return super.onOptionsItemSelected(item)
    }

}