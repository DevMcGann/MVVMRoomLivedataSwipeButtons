package com.gsoft.argentina.probandolivedataroommvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entidad(

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "nombre")
        val nombre: String
)


data class ListadeEntidades(val results: List<Entidad> = listOf())

/*fun List<Entidad>.toListadeEntidades(): ListadeEntidades {
        val resultList = mutableListOf<Entidad>()
        this.forEach { movieEntity ->
                resultList.add(movieEntity.toEntidad())
        }
        return ListadeEntidades(resultList)
}

fun MovieEntity.toMovie(): Movie =  Movie(
        this.id,
        this.adult,
        this.backdrop_path,
        this.original_title,
        this.original_language,
        this.overview,
        this.popularity,
        this.poster_path,
        this.release_date,
        this.title,
        this.video,
        this.vote_average,
        this.vote_count,
        this.movie_type
)*/

