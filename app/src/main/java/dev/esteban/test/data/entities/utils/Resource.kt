package dev.esteban.test.data.entities.utils

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val message: String? = null,
    val messageResource: Int? = null
)