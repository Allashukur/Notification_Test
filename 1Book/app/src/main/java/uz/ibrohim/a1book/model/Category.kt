package uz.ibrohim.a1book.model

import retrofit2.http.Field

data class Category(
    val id: String,
    val type: String,
    @Field("@id")
    val id2: Int,
    val name: String
)