package com.example.foodrater

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random
import kotlinx.android.synthetic.main.activity_main.*


class FoodAdapter(var context: Context, var view: View) : RecyclerView.Adapter<FoodViewHolder>() {

    private val allFoods = ArrayList<Food>()
    private val displayedFoods = ArrayList<Food>()
    private var lastRemovedPosition = 0

    init {
        allFoods.run {
            add(Food("Banana", R.drawable.banana))
            add(Food("Homemade Bread", R.drawable.bread))
            add(Food("Broccoli", R.drawable.broccoli))
            add(Food("Chicken", R.drawable.chicken))
            add(Food("Chocolate", R.drawable.chocolate))
            add(Food("Ice Cream", R.drawable.icecream))
            add(Food("Lima Beans", R.drawable.limabeans))
            add(Food("Steak", R.drawable.steak))
        }
    }

    override fun getItemCount() = displayedFoods.size

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): FoodViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_view, parent, false)
        return FoodViewHolder(view, this, context)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(displayedFoods[position])
    }

    fun edit(position: Int, rating: Float) {
        displayedFoods[position].rating = rating
        notifyItemChanged(position)
    }

    fun addFood(): String {
        if (!allFoods.isEmpty()) {
            val index = Random.nextInt(0, allFoods.size)
            val foodToAdd = allFoods.removeAt(index)
            displayedFoods.add(0, foodToAdd)
            notifyItemInserted(0)
            return foodToAdd.name
        }
        return "Out of food! Nothing"
    }

    fun onItemDismiss(position: Int) {
        lastRemovedPosition = position
        val removed = displayedFoods.removeAt(position)
        allFoods.add(removed)
        var snackbar = Snackbar.make(view,"${removed.name} removed", Snackbar.LENGTH_LONG)
        snackbar.setAction("UNDO", View.OnClickListener {
            reAddFood()
        }).show()
        notifyItemRemoved(position)
    }

    private fun reAddFood() {
        val foodToAdd = allFoods.removeAt(allFoods.size - 1)
        displayedFoods.add(lastRemovedPosition, foodToAdd)
        Snackbar.make(view, "${foodToAdd.name} added", Snackbar.LENGTH_LONG).show()
        notifyItemInserted(lastRemovedPosition)
    }
}
