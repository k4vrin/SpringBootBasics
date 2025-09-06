package dev.kavrin.boot4basics

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
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


    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onMethodAException(e: MethodArgumentNotValidException): ResponseEntity<Map<String, Any>> {
        val map = mutableMapOf<String, Any>()
        e.bindingResult.fieldErrors.forEach {
            map[it.field] = it.defaultMessage ?: "Invalid"
        }
        val parentMap = mapOf(
            "errorCode" to "VALIDATION_FAILED",
            "message" to "Validation failed for one or more fields",
            "errors" to map,
        )
        return ResponseEntity(parentMap, HttpStatus.BAD_REQUEST)
    }


}