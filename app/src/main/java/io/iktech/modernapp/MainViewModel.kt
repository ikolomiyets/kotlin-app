package io.iktech.modernapp

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.os.Handler

/**
 * Created by ikolomiyets on 22/01/2018.
 */
class MainViewModel : ViewModel() {
    var repoModel: RepoModel = RepoModel()
    var repositories = MutableLiveData<ArrayList<Repository>>()
    val text = ObservableField<String>("old data")
    val isLoading = ObservableField<Boolean>(false)

    fun loadRepositories(){
        isLoading.set(true)
        repoModel.getRepositories(object : OnRepositoryReadyCallback{
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }

}
