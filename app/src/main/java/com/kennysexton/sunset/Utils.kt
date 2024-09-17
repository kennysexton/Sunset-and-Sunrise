package com.kennysexton.sunset

// Takes any string and returns title case (ex. APPLES -> Apples || banana -> Banana
fun toTitleCase(string: String): String {
    return string.first().uppercase() + string.substring(1).lowercase()
}