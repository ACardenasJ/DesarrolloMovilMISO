package com.example.movilmisodreamteam2022.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.android.volley.VolleyError
import com.example.movilmisodreamteam2022.models.Coleccionista
import com.example.movilmisodreamteam2022.repositories.ColeccionistaRepository
import org.json.JSONObject

class ColeccionistaViewModel(application: Application) : AndroidViewModel(application) {

    private val coleccionistaRepository = ColeccionistaRepository(application)
    private val _coleccionistas = MutableLiveData<List<Coleccionista>>()
    private val _collector = MutableLiveData<Coleccionista>()

    val coleccionistas: LiveData<List<Coleccionista>>
        get() = _coleccionistas

    val coleccionista : LiveData<Coleccionista>
        get() = _collector

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    fun crearColeccionista(body: JSONObject, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit){
        coleccionistaRepository.postData(body, callback, onError)
    }

    fun refreshDataFromNetwork() {
        coleccionistaRepository.refreshData({
            _coleccionistas.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun getColeccionistabyIdFromNetwork(id: String) {
        coleccionistaRepository.getColeccionistabyId({
            _collector.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true

        },
            id)
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ColeccionistaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ColeccionistaViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}