package dev.esteban.test.data.entities.model

import com.squareup.moshi.Json

/**
 * Created by Esteban Barrios on 20/05/2019.
 */
data class ResponseListMovieVideos(
    @field:Json(name = "id")
    val id: Int = 0,
    @field:Json(name = "results")
    val results: List<Video> = listOf()
)

data class Video(
    @field:Json(name = "id")
    val id: String = "",
    @field:Json(name = "iso_639_1")
    val iso6391: String = "",
    @field:Json(name = "iso_3166_1")
    val iso31661: String = "",
    @field:Json(name = "key")
    val key: String = "",
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "site")
    val site: String = "",
    @field:Json(name = "size")
    val size: Int = 0,
    @field:Json(name = "type")
    val type: String = ""

)