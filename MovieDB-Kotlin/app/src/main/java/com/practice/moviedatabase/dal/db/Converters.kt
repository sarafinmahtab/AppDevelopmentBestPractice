package com.practice.moviedatabase.dal.db

import androidx.room.TypeConverter
import java.lang.StringBuilder

class Converters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromIntArray(genreIds: List<Int>): String {
            return StringBuilder().apply {
                for ((i, id) in genreIds.withIndex()) {
                    if (i == genreIds.lastIndex) this.append(id)
                    else this.append("$id,")
                }
            }.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toIntArray(ids: String): List<Int> {

            val genreIds = ArrayList<Int>()

            val idsArray = ids.split(",".toRegex(), 0)

            for (id in idsArray) {
                genreIds.add(id.toInt())
            }

            return genreIds
        }
    }
}
