package dev.esteban.test.data.entities.utils

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
sealed class ResourceState {
    object LOADING : ResourceState()
    object SUCCESS : ResourceState()
    object ERROR : ResourceState()
}
