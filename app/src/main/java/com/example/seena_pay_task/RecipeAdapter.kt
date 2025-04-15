package com.example.seena_pay_task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(private val recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageRecipe: ImageView = itemView.findViewById(R.id.imageRecipe)
        val titleRecipe: TextView = itemView.findViewById(R.id.titleRecipe)
        val ratingReviews: TextView = itemView.findViewById(R.id.ratingReviews)
        val chefName: TextView = itemView.findViewById(R.id.chefName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.imageRecipe.setImageResource(recipe.image)
        holder.titleRecipe.text = recipe.title
        holder.ratingReviews.text = "${recipe.rating} | ${recipe.reviews} Reviews"
        holder.chefName.text = recipe.chef
    }

    override fun getItemCount() = recipes.size
}