package com.example.movilmisodreamteam2022.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.android.volley.VolleyError
import com.example.movilmisodreamteam2022.models.Banda
import com.example.movilmisodreamteam2022.network.NetworkServiceAdapter
import com.example.movilmisodreamteam2022.repositories.ArtistaRepository
import org.json.JSONObject

class ArtistaViewModel(application: Application) :  AndroidViewModel(application) {

    private val artistaRepository = ArtistaRepository(application)

    private val _artistas = MutableLiveData<List<Banda>>()

    val artistas: LiveData<List<Banda>>
        get() = _artistas

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    fun crearArtista(body: JSONObject, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit){
        artistaRepository.postData(body, callback, onError)
    }

    fun refreshDataFromNetwork() {
        /*NetworkServiceAdapter.getInstance(getApplication()).getArtistas({
            _artistas.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })*/
        artistaRepository.refreshData({
            _artistas.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistaViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}