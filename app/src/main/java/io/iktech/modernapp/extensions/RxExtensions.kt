package io.iktech.modernapp.extensions

/**
 * Created by ikolomiyets on 26/01/2018.
 */
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}
