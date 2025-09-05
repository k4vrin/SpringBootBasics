package dev.kavrin.boot4basics

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/quotes")
class QuoteController {

    val quotes = mutableListOf<QuoteDto>()


    @GetMapping
    fun loadQuotes(
        @RequestParam("q", required = false) query: String?,
    ): List<QuoteDto> {
        return if (quotes.isNotEmpty()) {
            quotes.filter {
                it.content.contains(query ?: "", ignoreCase = true) || it.author.contains(query ?: "", ignoreCase = true)
            }
        } else {
            quotes
        }
    }

    @PostMapping
    fun postQuote(
        @RequestBody quoteDto: QuoteDto
    ): QuoteDto {
        quotes.add(quoteDto)
        return quoteDto
    }

    @PostMapping("/list")
    fun postListQuotes(
        @RequestBody quoteDtos: List<QuoteDto>
    ): List<QuoteDto> {
        quotes.addAll(quoteDtos)
        return quotes
    }

    @PutMapping()
    fun putQuote(
        @RequestBody quote: QuoteDto
    ): QuoteDto {
        val index = quotes.indexOfFirst { it.id == quote.id }
        if (index == -1) {
            quotes.add(quote)
        } else {
            quotes[index] = quote
        }
        return quote
    }

    @DeleteMapping("/{id}")
    fun deleteQuote(
        @PathVariable("id") id: Long
    ) {
        val quoteToDelete = quotes.find { it.id == id }
        if (quoteToDelete != null) {
            quotes.remove(quoteToDelete)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

}