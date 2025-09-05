package dev.kavrin.boot4basics

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class QuotesExceptionHandler {

    @ExceptionHandler(QuotesNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onQuotesNotFoundException(e: QuotesNotFoundException) = mapOf(
        "errorCode" to "QUOTE_NOT_FOUND",
        "message" to e.message
    )
}