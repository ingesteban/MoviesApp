package dev.esteban.test.data.entities.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
data class ResponseListMovies(
    @field:Json(name = "dates")
    val dates: Dates = Dates("", ""),
    @field:Json(name = "page")
    val page: Int = 0,
    @field:Json(name = "results")
    val results: List<Movies> = listOf(),
    @field:Json(name = "total_pages")
    val totalPages: Int = 0,
    @field:Json(name = "total_results")
    val totalResults: Int = 0
)

@Entity(tableName= "movies")
data class Movies(
    @PrimaryKey(autoGenerate = true)
    @field:Json(name = "id")
    val id: Int = 0,
    @field:Json(name = "adult")
    val adult: Boolean = false,
    @field:Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @field:Json(name = "original_language")
    val originalLanguage: String = "",
    @field:Json(name = "original_title")
    val originalTitle: String = "",
    @field:Json(name = "overview")
    val overview: String = "",
    @field:Json(name = "popularity")
    val popularity: Double = 0.0,
    @field:Json(name = "poster_path")
    val posterPath: String? = "",
    @field:Json(name = "release_date")
    val releaseDate: String = "",
    @field:Json(name = "title")
    val title: String = "",
    @field:Json(name = "video")
    val video: Boolean = false,
    @field:Json(name = "vote_average")
    val voteAverage: Double = 0.0,
    @field:Json(name = "vote_count")
    val voteCount: Int = 0,
    @field:Json(name = "type")
    var type:String = ""
)

data class Dates(
    @field:Json(name = "maximum")
    val maximum: String = "",
    @field:Json(name = "minimum")
    val minimum: String = ""
)