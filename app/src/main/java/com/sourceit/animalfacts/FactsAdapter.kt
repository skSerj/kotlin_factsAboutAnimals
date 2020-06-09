package com.sourceit.animalfacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sourceit.animalfacts.network.model.AnimalFacts
import kotlinx.android.synthetic.main.item_fact.view.*

class FactsAdapter() : RecyclerView.Adapter<FactsAdapter.FactsHolder>() {
    private val listOfFactsAboutAnimal: MutableList<AnimalFacts> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsHolder =
        FactsHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_fact, parent, false))

    override fun getItemCount() = listOfFactsAboutAnimal.size

    override fun onBindViewHolder(holder: FactsHolder, position: Int) {
        val animalFacts: AnimalFacts = listOfFactsAboutAnimal[position]
        holder.type.text = String.format("type: %s", animalFacts.type)
        holder.fact.text = String.format("fact: %s", animalFacts.text)
    }

    class FactsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val type: TextView = itemView.txt_type
        val fact: TextView = itemView.txt_fact
    }

    fun update(facts: List<AnimalFacts>) {
        listOfFactsAboutAnimal.apply {
            clear()
            addAll(facts)
        }
        notifyDataSetChanged()
    }
}