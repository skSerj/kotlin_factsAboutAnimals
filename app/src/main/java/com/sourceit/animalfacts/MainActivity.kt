package com.sourceit.animalfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sourceit.animalfacts.network.model.AnimalFacts
import com.sourceit.animalfacts.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var arrayFactsModel: MutableList<AnimalFacts> = ArrayList()
    private lateinit var disposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disposable = ApiService.data
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ facts -> saveInfo(facts) }) { throwable: Throwable -> showError(throwable) }
    }

    private fun saveInfo(facts: List<AnimalFacts>) {
        arrayFactsModel.addAll(facts)
        Toast.makeText(this, facts[0].text, Toast.LENGTH_LONG).show()
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }
}