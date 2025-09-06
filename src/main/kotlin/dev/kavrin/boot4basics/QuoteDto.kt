package dev.kavrin.boot4basics

import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

data class QuoteDto(
    val id: Long,
    @field:Length(
        min = 5,
        max = 255,
        message = "Quote must be between {min} and {max} characters long"
    )
    val content: String,
    @field:Pattern(
        regexp = "\\b[a-zA-Z]+\\b(?:\\s\\b[a-zA-Z]+\\b)+",
        message = "Author must contain at least first name and last name, only letters and spaces allowed"
    )
    val author: String,
//    @field:Password
//    val password: String,
)

