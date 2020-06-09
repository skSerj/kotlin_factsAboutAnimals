package com.sourceit.animalfacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sourceit.animalfacts.network.ApiServiceCallBack
import com.sourceit.animalfacts.network.model.AnimalFacts
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_animal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalFragment : Fragment() {
    var factsList = mutableListOf<AnimalFacts>()
    companion object {
        fun newInstance() = AnimalFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        txt_fact.text = factsList[1].text
        txt_type.text = factsList[1].type
        txt_v.text = factsList[1].v.toString()
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }
}
