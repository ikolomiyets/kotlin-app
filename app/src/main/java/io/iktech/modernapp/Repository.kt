package io.iktech.modernapp

import android.databinding.BaseObservable
import android.databinding.Bindable

/**
 * Created by ikolomiyets on 22/01/2018.
 */
class Repository(repositoryName: String, var repositoryOwner: String?, var numberOfStars: Int?, var hasIssues: Boolean = false) : BaseObservable(){

    @get:Bindable
    var repositoryName : String = repositoryName
    set(value) {
        field = value
        notifyPropertyChanged(BR.repositoryName)
    }
}
