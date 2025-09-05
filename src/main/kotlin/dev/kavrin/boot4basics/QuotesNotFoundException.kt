package dev.kavrin.boot4basics

class QuotesNotFoundException(
    private val id: Long,
) : RuntimeException(
    "A quote with id $id was not found"
)
