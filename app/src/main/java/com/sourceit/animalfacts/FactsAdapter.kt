package com.sourceit.animalfacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sourceit.animalfacts.network.model.AnimalFacts
import kotlinx.android.synthetic.main.item_fact.view.*

class FactsAdapter() : RecyclerView.Adapter<FactsAdapter.FactsHolder>() {
    private val originalList: MutableList<AnimalFacts> = ArrayList()
    private var filterList: MutableList<AnimalFacts> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_fact, parent, false)
        return FactsHolder(itemView)
    }

    override fun getItemCount() = filterList.size

    override fun onBindViewHolder(holder: FactsHolder, position: Int) {
        val animalFacts: AnimalFacts = filterList[position]
        holder.type.text = String.format("type: %s", animalFacts.type)
        holder.fact.text = String.format("fact: %s", animalFacts.text)
    }

    class FactsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val type: TextView = itemView.txt_type
        val fact: TextView = itemView.txt_fact
    }

    fun update(facts: List<AnimalFacts>) {
        originalList.apply {
            clear()
            addAll(facts)
        }
        filterList.apply {
            clear()
            addAll(facts)
        }
    }
}