package dev.kavrin.boot4basics.repository

import dev.kavrin.boot4basics.QuoteDto
import org.springframework.stereotype.Repository

@Repository
class QuotesRepository {
    val quotes = mutableListOf<QuoteDto>()

    fun loadQuotes(query: String?): List<QuoteDto> {
        return if (quotes.isNotEmpty()) {
            quotes.filter {
                it.content.contains(query ?: "", ignoreCase = true) || it.author.contains(query ?: "", ignoreCase = true)
            }
        } else {
            quotes
        }
    }

    fun postQuote(quoteDto: QuoteDto): QuoteDto {
        quotes.add(quoteDto)
        return quoteDto
    }

    fun postListQuotes(quoteDtos: List<QuoteDto>): List<QuoteDto> {
        quotes.addAll(quoteDtos)
        return quotes
    }

    fun putQuote(quote: QuoteDto): QuoteDto {
        val index = quotes.indexOfFirst { it.id == quote.id }
        if (index == -1) {
            quotes.add(quote)
        } else {
            quotes[index] = quote
        }
        return quote
    }

    fun deleteQuote(id: Long): Boolean {
        val quoteToDelete = quotes.find { it.id == id }
        return if (quoteToDelete != null) {
            quotes.remove(quoteToDelete)
        } else {
            false
        }
    }

}