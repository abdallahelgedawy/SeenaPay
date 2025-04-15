package com.example.seena_pay_task

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView
import com.example.seena_pay_task.R  // Use your app's R class, not android.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.easyRecipesRecyclerView)
        val egyptianRecyclerView = findViewById<RecyclerView>(R.id.egyptianRecipesRecyclerView)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val mainContentLayout = findViewById<View>(R.id.main_content_layout)
        val fragmentContainer = findViewById<View>(R.id.fragment_container)


        // Set up default fragment
        if (savedInstanceState == null) {
            showMainContent(mainContentLayout, fragmentContainer)
        }

        bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_home -> {
                    showMainContent(mainContentLayout, fragmentContainer)  // Show main activity layout
                    true
                }
                R.id.nav_search -> {
                    showFragment(EmptyFragment.newInstance("Search"), mainContentLayout, fragmentContainer)
                    true
                }
                R.id.nav_favorites -> {
                    showFragment(EmptyFragment.newInstance("Favorites"), mainContentLayout, fragmentContainer)
                    true
                }
                R.id.nav_profile -> {
                    showFragment(EmptyFragment.newInstance("Profile"), mainContentLayout, fragmentContainer)
                    true
                }
                else -> false
            }
        }

        fab.setOnClickListener {
            showFragment(EmptyFragment.newInstance("Add new Recipe"), mainContentLayout, fragmentContainer)
        }

        // Create recipe list matching your image
        val recipeList = listOf(
            Recipe(
                R.drawable.pasta,
                "Scrambled Egg",
                "4.9",
                "5k",
                "Gordon Ramsay"
            ),
            Recipe(
                R.drawable.burger,
                "Grilled Cheese Sandwich",
                "4.9",
                "4k",
                "Alain Ducasse"
            ),
            Recipe(
                R.drawable.salad,
                "Pasta with Garlic and Olive Oil",
                "4.8",
                "3.5k",
                "Yannick Alléno"
            )
        )

        // Set up horizontal layout manager
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        // Set adapter
        val adapter = RecipeAdapter(recipeList)
        recyclerView.adapter = adapter

        val egyptianRecipeList = listOf(
            Recipe(
                R.drawable.pasta,
                "Scrambled Egg",
                "4.9",
                "5k",
                "Gordon Ramsay"
            ),
            Recipe(
                R.drawable.burger,
                "Grilled Cheese Sandwich",
                "4.9",
                "4k",
                "Alain Ducasse"
            ),
            Recipe(
                R.drawable.salad,
                "Pasta with Garlic and Olive Oil",
                "4.8",
                "3.5k",
                "Yannick Alléno"
            )
        )

        egyptianRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        egyptianRecyclerView.adapter = RecipeAdapter(egyptianRecipeList)
    }

    // Add the missing loadFragment method
    private fun showFragment(fragment: Fragment, mainContent: View, fragmentContainer: View) {
        // Hide main content, show fragment container
        mainContent.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun showMainContent(mainContent: View, fragmentContainer: View) {
        // Show main content, hide fragment container
        mainContent.visibility = View.VISIBLE
        fragmentContainer.visibility = View.GONE

        // Clear any fragments from the container
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment != null) {
            supportFragmentManager
                .beginTransaction()
                .remove(currentFragment)
                .commit()
        }
    }
}

