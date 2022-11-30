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
    private val _artista = MutableLiveData<Banda>()

    val artistas: LiveData<List<Banda>>
        get() = _artistas

    val artista : LiveData<Banda>
        get() = _artista

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
        artistaRepository.refreshData({
            _artistas.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun getArtistaByIdFromNetwork(id: String) {
        artistaRepository.getArtistaByID(
            {
                _artista.postValue(it)
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
            if (modelClass.isAssignableFrom(ArtistaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistaViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}