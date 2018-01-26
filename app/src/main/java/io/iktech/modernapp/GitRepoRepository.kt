package io.iktech.modernapp

import android.content.Context
import android.os.Handler
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit


/**
 * Created by ikolomiyets on 22/01/2018.
 */
class GitRepoRepository (var netManager: NetManager) {
    val localDataSource = GitRepoLocalDataSource()
    val remoteDataSource = GitRepoRemoteDataSource()

    fun getRepositories() : Observable<ArrayList<Repository>>  {
        netManager.isConnectedToInternet?.let {
            if (it) {
                return remoteDataSource
                        .getRepositories()
                        .flatMap {
                            return@flatMap localDataSource.saveRepositories(it)
                                    .toSingleDefault(it)
                                    .toObservable()
                        }
            }
        }
        return localDataSource.getRepositories()
    }
}
