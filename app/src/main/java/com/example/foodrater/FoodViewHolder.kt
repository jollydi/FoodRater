package com.example.foodrater

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.food_view.view.*

class FoodViewHolder: RecyclerView.ViewHolder {

    val foodTextView: TextView = itemView.food_text
    val foodImageView: ImageView = itemView.food_image
    val foodRatingView: RatingBar = itemView.food_rating
    var context: Context

    constructor(itemView: View, adapter: FoodAdapter, context: Context): super(itemView) {
        this.context = context
        foodRatingView.setOnRatingBarChangeListener { _, rating, fromUser ->
            if (fromUser) {
                adapter.edit(adapterPosition, rating)
            }
        }


    }

    fun bind(food: Food) {
        foodTextView.text = food.name
        foodImageView.setImageResource(food.id)
        foodRatingView.rating = food.rating
    }

}
