package aaulia.compose.movie.data.local.model

import aaulia.compose.movie.data.remote.model.MovieCast
import androidx.room.Entity
import androidx.room.PrimaryKey

//@formatter:off
@Entity(tableName = "casts")
data class Cast(
    @PrimaryKey
    val castId      : Int,
    val name        : String,
    val character   : String,
    val image       : String
)
//@formatter:off

//@formatter:off
fun MovieCast.toCast() =
    Cast(
        castId      = id,
        name        = name,
        character   = character,
        image       = image.complete
    )
//@formatter:on