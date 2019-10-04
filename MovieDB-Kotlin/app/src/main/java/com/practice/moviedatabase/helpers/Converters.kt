package com.practice.moviedatabase.helpers

fun getGenreFromIds(hashMap: HashMap<Int, String>, genreIds: ArrayList<Int>): String {
    var genresText = ""

    for ((index, id) in genreIds.withIndex()) {
        genresText = if (index == genreIds.size-1) {
            genresText.plus(hashMap[id])
        } else {
            genresText.plus(hashMap[id]).plus(", ")
        }
    }
    return genresText
}
