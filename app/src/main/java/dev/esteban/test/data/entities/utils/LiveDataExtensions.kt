package dev.esteban.test.data.entities.utils

import androidx.lifecycle.MutableLiveData

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T? = null) = postValue(
    Resource(
        ResourceState.SUCCESS,
        data
    )
)

fun <T> MutableLiveData<Resource<T>>.setLoading() = postValue(
    Resource(
        ResourceState.LOADING,
        value?.data
    )
)

fun <T> MutableLiveData<Resource<T>>.setError(message: String? = null) {
    postValue(
        Resource(
            ResourceState.ERROR,
            value?.data,
            message
        )
    )

}


