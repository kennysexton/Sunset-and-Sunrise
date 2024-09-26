package com.kennysexton.sunset

// Takes any string and returns title case (ex. APPLES -> Apples || banana -> Banana
fun toTitleCase(string: String): String {
    return string.first().uppercase() + string.substring(1).lowercase()
}

// When getting the navigation route the route will appear with "?=" if there is a query parameter
fun trimQueryParameters(string: String): String {
    return string.split("?").first()
}