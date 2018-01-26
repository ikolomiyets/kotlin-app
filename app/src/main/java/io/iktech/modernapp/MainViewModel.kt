package io.iktech.modernapp

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import io.iktech.modernapp.extensions.plusAssign
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by ikolomiyets on 22/01/2018.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    var repoModel: GitRepoRepository = GitRepoRepository(NetManager(getApplication()))
    var repositories = MutableLiveData<ArrayList<Repository>>()
    val text = ObservableField<String>("old data")
    val isLoading = ObservableField<Boolean>(false)

    private val compositeDisposable = CompositeDisposable()

    fun loadRepositories() {
        isLoading.set(true)
        compositeDisposable += repoModel
                .getRepositories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<ArrayList<Repository>>() {

            override fun onError(e: Throwable) {
                //todo
            }

            override fun onNext(data: ArrayList<Repository>) {
                repositories.value = data
            }

            override fun onComplete() {
                isLoading.set(false)
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
