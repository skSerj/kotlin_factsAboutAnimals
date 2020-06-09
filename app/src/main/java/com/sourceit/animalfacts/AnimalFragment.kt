package com.sourceit.animalfacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sourceit.animalfacts.network.ApiServiceCallBack
import com.sourceit.animalfacts.network.model.AnimalFacts
import kotlinx.android.synthetic.main.animal_facts_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalFragment : Fragment() {
    var factsList = mutableListOf<AnimalFacts>()

    companion object {
        val factsAdapter: FactsAdapter = FactsAdapter()
        fun newInstance() = AnimalFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.animal_facts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        facts_list.apply {
            adapter = factsAdapter
            layoutManager = LinearLayoutManager(this.context)
        }

        ApiServiceCallBack.data
            .enqueue(object : Callback<List<AnimalFacts>> {

                override fun onResponse(
                    call: Call<List<AnimalFacts>>,
                    response: Response<List<AnimalFacts>>
                ) {
                    factsList.addAll(response.body() as List<AnimalFacts>)
                    showInfo(factsList)
                }

                override fun onFailure(call: Call<List<AnimalFacts>>, t: Throwable) {
                    showError(t)
                    t.printStackTrace()
                }
            })
    }

    private fun showInfo(factsList: MutableList<AnimalFacts>) {
        factsAdapter.update(factsList)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }
}
