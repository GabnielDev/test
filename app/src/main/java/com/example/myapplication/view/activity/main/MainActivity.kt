package com.example.myapplication.view.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import com.example.myapplication.view.fragment.beranda.BerandaFragment
import com.example.myapplication.view.fragment.search.SearchFragment
import com.example.myapplication.view.fragment.segera.SegeraFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val fragmentBeranda: Fragment = BerandaFragment()
    private val fragmentSearch: Fragment = SearchFragment()
    private val fragmentSegera: Fragment = SegeraFragment()

    private val fm: FragmentManager = supportFragmentManager

    var defaultFragment: Fragment = fragmentBeranda
    lateinit var menu: Menu
    lateinit var menuItem: MenuItem
    lateinit var botNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBotNav()

    }

    private fun setupBotNav() {
        fm.beginTransaction().add(R.id.flContainer, fragmentBeranda).show(fragmentBeranda).commit()
        fm.beginTransaction().add(R.id.flContainer, fragmentSearch).hide(fragmentSearch).commit()
        fm.beginTransaction().add(R.id.flContainer, fragmentSegera).hide(fragmentSegera).commit()

        botNav = findViewById(R.id.botNav)
        menu = botNav.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        botNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_beranda -> {
                    callFragment(0, fragmentBeranda)
                }
                R.id.menu_search -> {
                    callFragment(1, fragmentSearch)
                }
                R.id.menu_segera -> {
                    callFragment(2, fragmentSegera)
                }
            }
            false

        }

    }

    private fun callFragment(position: Int, fragment: Fragment) {
        menuItem = menu.getItem(position)
        menuItem.isChecked = true
        fm.beginTransaction().hide(defaultFragment).show(fragment).commit()
        defaultFragment = fragment
    }

}