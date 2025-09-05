package dev.kavrin.boot4basics.service

import dev.kavrin.boot4basics.QuoteDto
import dev.kavrin.boot4basics.QuotesNotFoundException
import dev.kavrin.boot4basics.repository.QuotesRepository
import org.springframework.stereotype.Service

@Service
class QuotesService(
    private val quotesRepository: QuotesRepository
) {
    fun loadQuotes(query: String?) = quotesRepository.loadQuotes(query)
    fun insertQuote(quote: QuoteDto) = quotesRepository.postQuote(quote)
    fun insertQuotes(quotes: List<QuoteDto>) = quotesRepository.postListQuotes(quotes)
    fun updateQuote(quote: QuoteDto) = quotesRepository.putQuote(quote)
    fun deleteQuote(id: Long) {
        val deleted = quotesRepository.deleteQuote(id)
        if (!deleted) {
            throw QuotesNotFoundException(id)
        }

    }

}