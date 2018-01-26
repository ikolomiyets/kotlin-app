package io.iktech.modernapp

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField

/**
 * Created by ikolomiyets on 22/01/2018.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    var repoModel: GitRepoRepository = GitRepoRepository(NetManager(getApplication()))
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
