package com.sourceit.animalfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sourceit.animalfacts.network.model.AnimalFacts
import com.sourceit.animalfacts.network.ApiServiceCallBack
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var factsList = mutableListOf<AnimalFacts>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                }
            })
    }

    private fun showInfo(factsList: MutableList<AnimalFacts>) {
        textView.text = factsList[1].text
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }
}