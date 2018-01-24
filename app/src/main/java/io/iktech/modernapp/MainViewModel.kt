package io.iktech.modernapp

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.os.Handler

/**
 * Created by ikolomiyets on 22/01/2018.
 */
class MainViewModel : ViewModel() {
    var repoModel: RepoModel = RepoModel()
    var repositories = ArrayList<Repository>()
    val text = ObservableField<String>("old data")
    val isLoading = ObservableField<Boolean>(false)

    fun refresh(){
        isLoading.set(true)
        repoModel.refreshData(object : OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                text.set(data)
            }})
    }

    fun loadRepositories(){
        isLoading.set(true)
        repoModel.getRepositories(object : OnRepositoryReadyCallback{
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories = data
            }
        })
    }

}
